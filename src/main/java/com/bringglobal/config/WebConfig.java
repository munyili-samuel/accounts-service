package com.bringglobal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bringglobal")
public class WebConfig {
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
