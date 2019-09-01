package com.jeanbritz.oauth.data.model;


import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails {

  @Id
  @Column(name = "client_id")
  private String clientId;

  @Column(name = "resource_ids")
  private String resourceIds;

  @Column(name = "client_secret")
  private String clientSecret;

  @Column(name = "scope")
  private String scope;

  @Column(name = "authorized_grant_types")
  private String authorizedGrantTypes;

  @Column(name = "web_server_redirect_uri")
  private String webServerRedirectUri;

  @Column(name = "authorities")
  private String authorities;

  @Column(name = "access_token_validity")
  private String accessTokenValidity;

  @Column(name = "refresh_token_validity")
  private String refreshTokenValidity;

  @Column(name = "additional_information")
  private String additionalInformation;

  @Column(name = "autoapprove")
  private String autoapprove;

}
