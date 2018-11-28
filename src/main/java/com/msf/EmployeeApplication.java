package com.msf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@ComponentScan()

/*@SpringBootApplication(scanBasePackages ="com.metlife")
@EnableEurekaServer*/

@SpringBootApplication
//@EnableDiscoveryClient
public class EmployeeApplication {

	
	public static void main(String[] args) {

		// System.setProperty("spring.config.name", "student-server");
		// System.setProperty("spring.config.name", "registration-server");
		SpringApplication.run(EmployeeApplication.class, args);
	}
	 
	

}
