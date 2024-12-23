package com.example.crisp_test.mvp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MvpApplication {

	public static void main(String[] args) {

		SpringApplication.run(MvpApplication.class, args);
		System.out.println("MVP application started");
	}

}
