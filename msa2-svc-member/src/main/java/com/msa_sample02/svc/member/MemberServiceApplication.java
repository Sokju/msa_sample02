package com.msa_sample02.svc.member;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.msa_sample02.svc.member.client.OrderMsgSource;

@EnableCircuitBreaker
@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(OrderMsgSource.class)
public class MemberServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(MemberServiceApplication.class, args);
	}
	
	@LoadBalanced //adding this line solved the issue
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
