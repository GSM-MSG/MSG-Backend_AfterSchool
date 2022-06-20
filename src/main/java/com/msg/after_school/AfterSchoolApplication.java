package com.msg.after_school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class AfterSchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(AfterSchoolApplication.class, args);
	}

}
