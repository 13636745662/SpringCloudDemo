package com.jarry.consumerticket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAutoConfiguration
@EnableFeignClients(basePackages ="com.jarry.consumerticket.service.feign")
@EnableHystrix
@ComponentScan(basePackages = {"com.jarry.consumerticket.controller","com.jarry.consumerticket.service.feign.fallback","com.jarry.consumerticket.webConfig"})
@RefreshScope
public class ConsumerTicketApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerTicketApplication.class, args);
    }
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
