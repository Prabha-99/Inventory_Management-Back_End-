package com.example.monara_backend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MonaraBackEndApplication implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(MonaraBackEndApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Starting Code!");

		//Create Object;
	}
}
