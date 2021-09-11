package com.antra.ting.weather.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class Config {
    @Bean
    public String hello(){
        return "creating bean.";

    }

    @Bean  //convert results (calling api results: string) )to class
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newFixedThreadPool(5);
    }
}

