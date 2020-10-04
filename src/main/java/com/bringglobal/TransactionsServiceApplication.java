package com.bringglobal;

import org.apache.catalina.startup.Tomcat;

public class TransactionsServiceApplication {
  public static void main(String[] args) throws Exception {

    // this is the default port
    String targetPort = "8080";
    String port = System.getenv("PORT");
    String appBase = ".";
    Tomcat tomcat = new Tomcat();

    // if a port has been specified as an Env variable, replace the default
    if (port != null) {
      targetPort = port;
    }
    tomcat.setPort(Integer.valueOf(targetPort));
    tomcat.getHost().setAppBase(appBase);

    // Set the service name here
    tomcat.addWebapp("/v1/current-accounts", appBase);
    tomcat.start();
    tomcat.getServer().await();
  }
}
