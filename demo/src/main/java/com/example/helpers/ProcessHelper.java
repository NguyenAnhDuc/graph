package com.example.helpers;

import com.example.DemoApplication;
import com.example.models.PersonRepository;
import com.example.models.User;
import com.example.models.UserDao;
import com.example.models.nodes.*;
import com.example.models.repositories.*;
import org.apache.commons.validator.Validator;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

/**
 * Created by ducna on 2/8/2017.
 */
public class ProcessHelper {

    private final static String prefix_redis_vne = "vne:";
    private final static String prefix_redis_violympic = "violympic:";
    private final static Logger log = LoggerFactory.getLogger(ProcessHelper.class);
    public static void fromMySqlToNeo4j(UserDao userDao, FptIdRepository fptIdRepository, VioIdRepository vioIdRepository, EmailRepository emailRepository, PhoneRepository phoneRepository,
                                        ProvinceRepository provinceRepository, DistrictRepository districtRepository, AddressRepository addressRepository){
        List<User> users;
        //users = userDao.getAll().subList(1,100);
        users = userDao.getAll();
        int count = 0;
        for (User user : users) {
                count ++;
                log.info("======================= PROCESSING USER: " + count + "=======================");
				if (user == null ) continue;
				VioId vioId = vioIdRepository.findByVioId(user.getPk_user_id());
				if (vioId != null) continue;
				vioId = new VioId(user.getPk_user_id());
				FptId fptId = null;
				// phone
				if (FunctionHelper.isValidPhone(user.getS_phone())){
					Phone phone = phoneRepository.findByPhone(user.getS_phone());
					if (phone == null) {
						phone = new Phone(user.getS_phone());
						fptId = new FptId(UUID.randomUUID().toString());
					}
					else {
						fptId = phone.fptId;
					}
					vioId.hasPhone(phone);
					fptId.hasPhone(phone);
				}

				// email
				if (EmailValidator.getInstance().isValid(user.getS_email())){
					Email email = emailRepository.findByEmail(user.getS_email());
					if (email == null) {
						email =  new Email(user.getS_email());
						if (fptId == null) fptId = new FptId(UUID.randomUUID().toString());
					}
					else{
                        fptId = email.fptId;
					}
					vioId.hasEmail(email);
					fptId.hasEmail(email);
				}

				//xet tiep khi co email hoac phone
				if (fptId != null){
					// Province
                    Province province = null;
					if (FunctionHelper.isValidProvince(user.getS_province_name())){
						province = provinceRepository.findByProvince(user.getS_province_name());
						if (province == null) province = new Province(user.getS_province_name());
                        //province = new Province(user.getS_province_name());
						fptId.hasProvince(province);
						vioId.hasProvince(province);
					}

					District district = null;
					if (FunctionHelper.isValidDistrict(user.getS_village_name())){
                        district = districtRepository.findByDistrict(user.getS_village_name());
                        if (district == null) district = new District(user.getS_village_name());
                        //district = new District(user.getS_village_name());
                        fptId.hasDistrict(district);
                        if (province != null) {
                            province.belongToDistrict(district);
                        }
                    }
					// Address
					if (FunctionHelper.isValidAddress(user.getS_address())){
						Address address = addressRepository.findByAddress(user.getS_address());
						if (address == null) address = new Address(user.getS_address());
						// address = new Address(user.getS_address());
						fptId.hasAddress(address);
						vioId.hasAddress(address);
						if (district != null){
						    district.belongToAddress(address);
                        }
					}
					fptId.linkToVioId(vioId);
					fptIdRepository.save(fptId);
				}
			}
    }

    public static void testFromMySqlToNeo4j(UserDao userDao, FptIdRepository fptIdRepository, VioIdRepository vioIdRepository, EmailRepository emailRepository, PhoneRepository phoneRepository,
                                        ProvinceRepository provinceRepository, DistrictRepository districtRepository, AddressRepository addressRepository){
        List<User> users;
        users = userDao.getAll();
        int count = 0;
        for (User user : users) {
            count ++;
            log.info("======================= PROCESSING USER: " + count + "=======================");
            if (user == null ) continue;
            FptId fptId = new FptId(UUID.randomUUID().toString());
            VioId vioId = null;
            vioId = vioIdRepository.findByVioId(user.getPk_user_id());
            if (vioId == null) vioId = new VioId(user.getPk_user_id());
            Phone phone = new Phone(user.getS_phone());
            Email email = new Email(user.getS_email());
            Province province = new Province(user.getS_province_name());
            District district = new District(user.getS_village_name());
            Address address = new Address(user.getS_address());
            vioId.hasPhone(phone);
            vioId.hasEmail(email);
            vioId.hasProvince(province);
            vioId.hasAddress(address);

            fptId.hasPhone(phone);
            fptId.hasEmail(email);
            fptId.hasProvince(province);
            fptId.hasAddress(address);

            fptId.linkToVioId(vioId);
            fptIdRepository.save(fptId);
            }
    }

    private static void importVioMapping(String stVioId, String stBrowserId,  FptIdRepository fptIdRepository, VioIdRepository vioIdRepository, BrowserIdRepository browserIdRepository){
        try{
            int vio_id = Integer.parseInt(stVioId);
            VioId vioId = vioIdRepository.findByVioId(vio_id);
            if (vioId == null) return;
            BrowserId browserId = browserIdRepository.findByBrowserId(stBrowserId);
            if (browserId == null) browserId = new BrowserId(stBrowserId);
            vioId.linkToBrowserId(browserId);
            browserId.linkToFptId(vioId.fptId);
            browserIdRepository.save(browserId);
            vioId.fptId.linkToBrowserId(browserId);
            fptIdRepository.save(vioId.fptId);
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    private static void importVneMapping(String stVneId, String stBrowserId, FptIdRepository fptIdRepository, VneIdRepository vneIdRepository, BrowserIdRepository browserIdRepository,
                                         EmailRepository emailRepository, StringRedisTemplate stringRedisTemplate){
        try{
            if (FunctionHelper.isValidVneId(stVneId)){
                VneId vneId = vneIdRepository.findByVneId(stVneId);
                if (vneId != null) return;
                if (!stringRedisTemplate.hasKey(prefix_redis_vne + stVneId)) return;
                // Chi xet khi vneid chua ton tai va vneid day co email
                vneId = new VneId(stVneId);
                FptId fptId = null;
                Email email = null;
                BrowserId browserId = null;
                String stEmail = stringRedisTemplate.opsForValue().get(prefix_redis_vne+vneId.getVneId());
                if (!EmailValidator.getInstance().isValid(stEmail))  return ;
                email = emailRepository.findByEmail(stEmail);
                browserId = browserIdRepository.findByBrowserId(stBrowserId);
                if (email == null && browserId == null) {
                    email = new Email(stEmail);
                    fptId = new FptId(UUID.randomUUID().toString());
                }
                else {
                    if (email != null) fptId = email.fptId;
                    else fptId = browserId.fptId;
                }
                if (browserId == null) browserId = new BrowserId(stBrowserId);
                vneId.hasEmail(email);
                vneId.linkToBrowserId(browserId);
                fptId.hasEmail(email);
                fptId.linkToVneId(vneId);
                fptId.linkToBrowserId(browserId);
                fptIdRepository.save(fptId);
            }
        }
        catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    public static void importMapping(String filename, FptIdRepository fptIdRepository, VneIdRepository vneIdRepository, VioIdRepository vioIdRepository, BrowserIdRepository browserIdRepository,
                                     EmailRepository emailRepository, StringRedisTemplate stringRedisTemplate) throws Exception{
        Scanner s = new Scanner(new File(filename));
        ArrayList<String> lines = new ArrayList<String>();
        while (s.hasNext()){
            lines.add(s.next());
        }
        s.close();
        int count = 0;
        for (String line : lines){
            count ++;
            log.info("============================" + "Processing line: " + count + "============================");
            try{
                if (line.contains("violympic")){
                    String stVioid = line.replace("(","").replace(")","").replace("violympic:","").split(",")[1];
                    String stBrowserId = line.replace("(","").replace(")","").replace("violympic:","").split(",")[0];
                    importVioMapping(stVioid,stBrowserId, fptIdRepository, vioIdRepository, browserIdRepository );
                }
                else if (line.contains("vne")) {
                    String stVneId = line.replace("(","").replace(")","").replace("vne:","").split(",")[1];
                    String stBrowserId = line.replace("(","").replace(")","").replace("vne:","").split(",")[0];
                    importVneMapping(stVneId,stBrowserId, fptIdRepository, vneIdRepository , browserIdRepository, emailRepository, stringRedisTemplate );
                }
            }
            catch (Exception ex){
                log.error(ex.getMessage());
            }


        }
    }

    public static void main(String[] args){

    }
}
