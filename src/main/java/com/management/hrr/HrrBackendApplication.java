package com.management.hrr;

import com.management.hrr.service.DefaultDataSeeder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HrrBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(HrrBackendApplication.class, args);
	}
	@Bean
	public ApplicationRunner dataLoader(DefaultDataSeeder dataSeeder) {
		return args -> dataSeeder.seedData();
	}

}
