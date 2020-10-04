package com.bringglobal.config;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Initializes and enables Spring Framework MVC.
 * Additionally, we add any other Beans that we would like to register here.
 * Since this class is configures in the {@link AppInitializer} it will be used to scan out packages for
 * REST Endpoints upon initialization
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bringglobal")
public class WebConfig {
  private final Logger logger = Logger.getLogger(WebConfig.class);

  /**
   * Add a bean for RestTemplate to allow @Autowiring it
   *
   * @return RestTemplate
   */
  @Bean
  public RestTemplate restTemplate() {
    logger.info("Registering RestTemplate Bean");
    return new RestTemplate();
  }
}
