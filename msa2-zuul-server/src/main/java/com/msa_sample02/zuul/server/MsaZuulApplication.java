package com.msa_sample02.zuul.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.msa_sample02.zuul.server.filter.SimpleErrorFilter;
import com.msa_sample02.zuul.server.filter.SimplePostFilter;
import com.msa_sample02.zuul.server.filter.SimplePreFilter;
import com.msa_sample02.zuul.server.filter.SimpleRouteFilter;


@EnableZuulProxy
@EnableDiscoveryClient
@EnableHystrix
@SpringBootApplication
public class MsaZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsaZuulApplication.class, args);
	}

	@Bean
    public SimplePreFilter preFilter() {
        return new SimplePreFilter();
    }
    
    @Bean
    public SimplePostFilter postFilter() {
        return new SimplePostFilter();
    }
    
    @Bean
    public SimpleErrorFilter errorFilter() {
        return new SimpleErrorFilter();
    }
    
    @Bean
    public SimpleRouteFilter routeFilter() {
        return new SimpleRouteFilter();
    }
    
}
