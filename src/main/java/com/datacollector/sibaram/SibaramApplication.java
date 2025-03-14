package com.datacollector.sibaram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SibaramApplication {

	public static void main(String[] args) {
		SpringApplication.run(SibaramApplication.class, args);
		System.out.println("Data collector main() running");
	}

}
