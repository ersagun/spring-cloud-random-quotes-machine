package com.sfeir.randomquotemachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
//import org.springframework.cloud.sleuth.sampler.AlwaysSampler;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@EnableEurekaClient
@EnableCircuitBreaker
@SpringBootApplication
public class RandomQuoteMachineApplication {

    public static void main(String[] args) {
        SpringApplication.run(RandomQuoteMachineApplication.class, args);
    }

/*    @Bean
    public AlwaysSampler defaultSampler() {
        return new AlwaysSampler();
    }*/
}
