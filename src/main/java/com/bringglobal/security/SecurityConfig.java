package com.bringglobal.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private final static Logger logger = Logger.getLogger(SecurityConfig.class);

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    logger.info("Setting basic auth users");
    auth.inMemoryAuthentication().withUser("sara").password("{noop}sara").roles("USER");
    auth.inMemoryAuthentication().withUser("peter").password("{noop}peter").roles("USER");
    auth.inMemoryAuthentication().withUser("alex").password("{noop}alex").roles("ADMIN");
  }
}