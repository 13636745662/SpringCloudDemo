package com.jarry.serviceutils;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServiceUtilsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceUtilsApplication.class, args);
    }

}
