package com.bringglobal;

import org.apache.catalina.startup.Tomcat;

/**
 * Entry point.
 * The Tomcat Service is started here.
 * Since the app is using embedded Tomcat Server,
 * it needs to to be started explicitly.
 *
 * <code><bold>Tip</bold></code>Using this method ensures that the app will be run using Maven or Java commands rather than Tomcat command
 */
public class TransactionsServiceApplication {
  public static void main(String[] args) throws Exception {

    // this is the default port
    String targetPort = "8080";
    String port = System.getenv("PORT");
    String appBase = ".";
    Tomcat tomcat = new Tomcat();

    // if a port has been specified in the env, replace the default
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
