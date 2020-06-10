package com.msa_sample02.dashboard.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
@EnableHystrixDashboard
public class MsaDashboardServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaDashboardServerApplication.class, args);
	}
	

}
