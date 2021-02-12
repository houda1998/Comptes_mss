package org.sid.getwayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;

import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GetawayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetawayServiceApplication.class, args);
    }

    @Bean
    DiscoveryClientRouteDefinitionLocator
    dynamicRoutes(ReactiveDiscoveryClient rdc,DiscoveryLocatorProperties dlp){
        return new DiscoveryClientRouteDefinitionLocator(rdc,dlp);
    }

}

