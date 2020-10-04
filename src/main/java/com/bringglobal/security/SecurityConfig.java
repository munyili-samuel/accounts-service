package com.bringglobal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private AuthenticationProvider provider;

  public SecurityConfig(final AuthenticationProvider authenticationProvider) {
    super();
    this.provider = authenticationProvider;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider);
  }

  @Override
  public void configure(final WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers("/login/**");
  }


  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth
      .inMemoryAuthentication()
      .withUser("user").roles("BACKBASE").password("{noop}password");
  }
}