package com.msa_sample02.svc.order.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Contact;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.SwaggerDefinition;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@Profile("!prod")
@Configuration
@EnableSwagger2
@SwaggerDefinition(
        info = @Info(description = "MSA-SAMPLE02",
                version = "1.0.0",
                title = "MSA-SAMPLE02",
                termsOfService = "Term of Service",
                contact = @Contact(
                        name = "Crossent",
                        url = "https://www.crossent.com/",
                        email = "mail@crossent.com"),
                license = @License(name = "Apache License Version 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0")
        )
)
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())     		// 현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
          .paths(PathSelectors.any())                   // 모든 URL들을 노출
          //.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
          //.apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.cloud")))
          //.apis(RequestHandlerSelectors.basePackage("com.msa_sample01.web.controller"))	// 특정 패키지 필터링
          //.paths(PathSelectors.ant("/api/**")) 			// 그중 /api/** 인 URL들만 필터링
          .build();                                           
    }
}