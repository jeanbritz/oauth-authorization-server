package com.jeanbritz.oauth.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.oauth2.config.annotation.configurers.*;
import org.springframework.security.oauth2.config.annotation.web.configuration.*;
import org.springframework.security.oauth2.config.annotation.web.configurers.*;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.approval.*;
import org.springframework.security.oauth2.provider.code.*;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.*;


import javax.sql.*;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter  {

  private DataSource dataSource;
  private AuthenticationManager authenticationManager;
  private PasswordEncoder passwordEncoder;
  private UserDetailsService userDetailsService;

  @Autowired
  public AuthorizationServerConfig(final DataSource dataSource, final AuthenticationManager authenticationManager,
                                   PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
    this.dataSource = dataSource;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
    this.userDetailsService = userDetailsService;
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security.passwordEncoder(passwordEncoder).tokenKeyAccess("permitAll()").checkTokenAccess(
            "isAuthenticated()");
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.jdbc(dataSource);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.authenticationManager(authenticationManager)
            .userDetailsService(userDetailsService)
            .tokenStore(tokenStore());
  }

  @Bean
  public TokenStore tokenStore() {
    return new JdbcTokenStore(dataSource);
  }

  @Bean
  public ApprovalStore approvalStore() {
    return new JdbcApprovalStore(dataSource);
  }

  @Bean
  public AuthorizationCodeServices authorizationCodeServices() {
    return new JdbcAuthorizationCodeServices(dataSource);
  }

  @Bean
  public DefaultTokenServices tokenServices(final TokenStore tokenStore, final ClientDetailsService clientDetailsService) {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setTokenStore(tokenStore);
    defaultTokenServices.setClientDetailsService(clientDetailsService);
    defaultTokenServices.setAuthenticationManager(this.authenticationManager);
    return defaultTokenServices;
  }


}
