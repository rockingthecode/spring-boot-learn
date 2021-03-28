package com.rockingthecode.springboot.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "publisher")
    Publisher getPublisher(){
       return new Publisher();
    }
}
