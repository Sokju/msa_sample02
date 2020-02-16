package com.msa_sample02.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MsaEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaEurekaServerApplication.class, args);
	}
}
