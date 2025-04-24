package com.peterpet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.peterpet.demo.module.test.ApiTest;

@EnableScheduling
@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class PeterpetApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(PeterpetApplication.class, args);
	}

}
