package com.ah.apartowner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.ah.aparowner", "com.ah.tablesynclib" })
public class ApartownerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApartownerApplication.class, args);
	}

}
