package com.kwebdev.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class CustomerConfig {
    @Bean
    @LoadBalanced // required for Eureka
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
