package com.Ilker.inevntory_service;

import org.springframework.boot.SpringApplication;

public class TestInevntoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(InevntoryServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
