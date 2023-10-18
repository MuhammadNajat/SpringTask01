package com.example.WebFormHandling.Configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    @Bean
    public RestTemplate restTemplate() {
        System.out.println("RestTemplate is instantiating");
        return new RestTemplate();
    }
}
