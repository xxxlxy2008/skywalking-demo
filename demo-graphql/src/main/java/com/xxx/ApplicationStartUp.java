package com.xxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ApplicationStartUp {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationStartUp.class, args);
	}
}
