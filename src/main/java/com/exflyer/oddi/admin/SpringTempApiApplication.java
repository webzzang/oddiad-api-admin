package com.exflyer.oddi.admin;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@Slf4j
@EnableCaching
public class SpringTempApiApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {

    SpringApplication.run(SpringTempApiApplication.class, args);
    log.info("start!! admin-api");
  }

}
