package com.ah.residence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.ah.residence", "com.ah.tablesynclib", "com.ah.commonlib", "com.ah.backendreadlib" })
public class ResidenceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResidenceApplication.class, args);
	}

}
