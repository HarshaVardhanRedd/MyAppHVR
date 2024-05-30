package com.hvr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class HvrFarmsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HvrFarmsApplication.class, args);
	}

}
