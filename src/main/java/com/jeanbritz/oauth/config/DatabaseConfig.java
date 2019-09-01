package com.jeanbritz.oauth.config;

import liquibase.integration.spring.*;
import lombok.extern.slf4j.*;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

import javax.sql.*;

@Configuration
@Slf4j
@EnableJpaRepositories(basePackages = "com.jeanbritz.oauth.data")
public class DatabaseConfig {

  @Bean(name = "liquibase")
  public SpringLiquibase liquibase(DataSource dataSource) {
    SpringLiquibase liquibase = new SpringLiquibase();
    liquibase.setChangeLog("classpath:db/changelog/master.xml");
    liquibase.setDataSource(dataSource);
    log.info("Liquibase: {}", liquibase.getChangeLog());
    return liquibase;
  }

}
