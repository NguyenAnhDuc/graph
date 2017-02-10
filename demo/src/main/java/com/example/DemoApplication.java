package com.example;

import com.example.models.UserDao;
import com.example.models.repositories.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

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
								  ProvinceRepository provinceRepository, DistrictRepository districtRepository, AddressRepository addressRepository, BrowserIdRepository browserIdRepository) {
		return (args) -> {

			/*personRepository.deleteAll();
			fptIdRepository.deleteAll();
			emailRepository.deleteAll();
			phoneRepository.deleteAll();*/

			/*FptId fptId = new FptId("id1");
			Email email = new Email("email1");
			Phone phone = new Phone("0913111111");
			Phone phone2 = new Phone("09866554545");
			fptId.hasEmail(email);
			//fptId.hasPhone(phone);
			//fptId.hasPhone(phone2);
			fptIdRepository.save(fptId);
			//emailRepository.save(email);
			Email email2 = new Email("email2");
			fptId.hasEmail(email2);
			fptIdRepository.save(fptId);*/

			/*Email email = emailRepository.findByEmail("email1");
			System.out.println(email.fptId.getFptId());*/
			//System.out.println(email.fptId.getTmpId());

			//ProcessHelper.fromMySqlToNeo4j(userDao,fptIdRepository,vioIdRepository,emailRepository,phoneRepository,provinceRepository,districtRepository, addressRepository);

			//ProcessHelper.importVioMapping("input/identify",fptIdRepository, vioIdRepository, browserIdRepository);
			System.out.println("Hello World");
		};
	}
}
