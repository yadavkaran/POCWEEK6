package com.poc6.neosoft;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class MainApp{

	public static void main(String[] args) {
		SpringApplication.run(MainApp.class, args);
		log.info("MainApp is started.");
	}

}

	