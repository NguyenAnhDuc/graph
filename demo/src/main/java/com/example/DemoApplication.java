package com.example;

import com.example.helpers.ProcessHelper;
import com.example.models.UserDao;
import com.example.models.nodes.Province;
import com.example.models.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.List;
import java.util.UUID;

@SpringBootApplication
@EnableNeo4jRepositories
public class DemoApplication {

	private final static Logger log = LoggerFactory.getLogger(DemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(UserDao userDao, FptIdRepository fptIdRepository, VioIdRepository vioIdRepository, EmailRepository emailRepository, PhoneRepository phoneRepository,
								  ProvinceRepository provinceRepository, DistrictRepository districtRepository, AddressRepository addressRepository, BrowserIdRepository browserIdRepository,
								  VneIdRepository vneIdRepository, StringRedisTemplate stringRedisTemplate) {
		return (args) -> {

			if (args[0].toUpperCase().equals("MYSQL_TO_NEO4J")){
				log.info("IMPORT VIO FROM MYSQL TO NEO4J");
				ProcessHelper.fromMySqlToNeo4j(userDao,fptIdRepository,vioIdRepository,emailRepository,phoneRepository,provinceRepository,districtRepository, addressRepository);
				//ProcessHelper.testFromMySqlToNeo4j(userDao,fptIdRepository,vioIdRepository,emailRepository,phoneRepository,provinceRepository,districtRepository, addressRepository);
			}
			if (args[0].toUpperCase().equals("TEST")){
				//Province province = new Province("Hà Nội");
				//provinceRepository.save(province);

				log.info("TEST FROM MAIN");
				Province province = provinceRepository.findByProvince("Hà Nội");
				if (province == null)
					System.out.println("Khong co");
				else
					System.out.println(province.getProvince());
			}

			if (args[0].toUpperCase().equals("IMPORT_MAPPING")){
				//StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
				ProcessHelper.importMapping(args[1].trim(),fptIdRepository, vneIdRepository, vioIdRepository, browserIdRepository, emailRepository, stringRedisTemplate);
			}
			System.out.println("DONE");
		};
	}
}
