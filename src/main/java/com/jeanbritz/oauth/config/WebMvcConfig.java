package com.jeanbritz.oauth.config;

import org.springframework.web.servlet.config.annotation.*;

@EnableWebMvc
//@Configuration - this annotation causes problems. Need to figure out why still
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/webjars/**")
            .addResourceLocations("/webjars/**").resourceChain(false);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }
}
