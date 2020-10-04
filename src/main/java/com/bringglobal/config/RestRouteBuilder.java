package com.bringglobal.config;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class RestRouteBuilder extends RouteBuilder {

  @Override
  public void configure() {
    System.err.println("This was configured");
//    restConfiguration().component("restlet").host("localhost:8080");
    rest("/camel").get().route().transform().constant("Hello World");
    from("timer:aTimer?fixedRate=true&period=10s")
        .setHeader(Exchange.HTTP_METHOD, constant("GET"))
        .to("ahc:https://restcountries.p.mashape.com/callingcode/90");
  }
}
