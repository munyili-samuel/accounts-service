package com.bringglobal;

import org.apache.catalina.startup.Tomcat;

public class TransactionsServiceApplication {
  public static void main(String[] args) throws Exception {
    String port = System.getenv("PORT");
    String appBase = ".";
    Tomcat tomcat = new Tomcat();

    if (port != null) {
      tomcat.setPort(Integer.valueOf(port));
    }
    tomcat.getHost().setAppBase(appBase);
    tomcat.addWebapp("", appBase);
    tomcat.start();
    tomcat.getServer().await();
  }
}
