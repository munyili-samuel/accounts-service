package com.bringglobal.config;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Since we are not using spring boot to bootstrap the app, we need to configure the servlets.
 * This class replaces web.xml and it serves to initialize our DispatcherServlets.
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
  private final Logger logger = Logger.getLogger(AppInitializer.class);
  /**
   * Sets roots configuration class
   *
   * @return Class[] - List of all root classes
   */
  @Override
  protected Class[] getRootConfigClasses() {
    return new Class[] {WebConfig.class};
  }

  /**
   * Allows for explicitly adding servlet classes.
   * We do not need to pass anything here as no additional servlet config classes
   *
   * @return Class[]
   */
  @Override
  protected Class[] getServletConfigClasses() {
    return null;
  }

  /**
   * Maps URLs for servlets.
   * Since all the routes start from `/` providing that alone gets the job done.
   *
   * @return String[]
   */
  @Override
  protected String[] getServletMappings() {
    logger.info("Setting servlet mappings");
    return new String[] {"/"};
  }
}
