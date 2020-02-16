package com.msa_sample02.turbine.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableTurbineStream
@EnableHystrixDashboard
public class MsaTurbineServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaTurbineServerApplication.class, args);
	}
	

}
