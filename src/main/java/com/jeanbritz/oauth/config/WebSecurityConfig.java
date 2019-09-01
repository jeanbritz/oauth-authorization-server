package com.jeanbritz.oauth.config;

import com.jeanbritz.oauth.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.jdbc.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.access.*;

import javax.sql.*;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private DataSource dataSource;
  private AccessDeniedHandler accessDeniedHandler;

  @Autowired
  public WebSecurityConfig(final DataSource dataSource, final AccessDeniedHandler accessDeniedHandler) {
    this.dataSource = dataSource;
    this.accessDeniedHandler = accessDeniedHandler;
  }

  @Bean
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return DefaultPasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  @Bean
  @Override
  public UserDetailsService userDetailsService() {
    JdbcDaoImpl jdbcDao = new JdbcDaoImpl();
    jdbcDao.setDataSource(dataSource);
    return jdbcDao;
  }

  @Override
  public void init(WebSecurity web) throws Exception {
    super.init(web);
    web.debug(true);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.httpBasic().disable()
//            .anonymous().disable()
            .authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/webjars/**").permitAll()
//            .antMatchers("/").permitAll()
//            .antMatchers("/admin/**").hasAnyRole("ADMIN")
//            .antMatchers("/user/**").hasAnyRole("USER")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .loginProcessingUrl("/login")
            .defaultSuccessUrl("/")
            .failureUrl("/login?error=true")
            .permitAll()
            .and()
            .exceptionHandling().accessDeniedHandler(accessDeniedHandler)
    ;
  }
}
