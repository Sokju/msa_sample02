package com.msa_sample02.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.msa_sample02.zuul.server.filter.SimpleFilter;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class MsaZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaZuulApplication.class, args);
	}

	@Bean
	public SimpleFilter simpleFilter() {
		return new SimpleFilter();
	}
}
