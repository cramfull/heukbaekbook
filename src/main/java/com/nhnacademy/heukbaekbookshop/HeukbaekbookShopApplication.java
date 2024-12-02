package com.nhnacademy.heukbaekbookshop;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableDiscoveryClient
public class HeukbaekbookShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeukbaekbookShopApplication.class, args);
	}

}
