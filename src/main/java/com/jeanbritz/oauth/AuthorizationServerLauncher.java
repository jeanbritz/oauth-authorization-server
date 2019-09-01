package com.jeanbritz.oauth;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.web.server.*;
import org.springframework.boot.web.servlet.server.*;
import org.springframework.context.annotation.*;

@SpringBootApplication
public class AuthorizationServerLauncher {

  public static void main(String[] args) {
    SpringApplication.run(AuthorizationServerLauncher.class, args);
  }

  @Bean
  public WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> webServerFactoryCustomizer() {
    return factory -> factory.setContextPath("");
  }

}
