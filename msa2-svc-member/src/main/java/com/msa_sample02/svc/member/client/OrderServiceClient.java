package com.msa_sample02.svc.member.client;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msa_sample02.svc.member.config.RibbonConfig;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@RibbonClient(name = "msa2-svc-order" , configuration = RibbonConfig.class)
@Service
public class OrderServiceClient {

    private final RestTemplate restTemplate;

    public OrderServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getFallbackName", commandProperties = {
       @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000") })
    public void order(String member) {
        this.restTemplate.postForObject("http://msa2-svc-order:9092/v2/order", member, String.class);
    }

    private String getFallbackName() {
        return "Fallback";
    }
}