package com.jeanbritz.oauth.service;

import com.jeanbritz.oauth.data.model.*;
import com.jeanbritz.oauth.data.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.oauth2.provider.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CommonService {

  private ClientDetailsService clientDetailsService;
  private OAuthClientDetailsRepository oAuthClientDetailsRepository;

  @Autowired
  public CommonService(final ClientDetailsService clientDetailsService, OAuthClientDetailsRepository oAuthClientDetailsRepository) {
    this.clientDetailsService = clientDetailsService;
    this.oAuthClientDetailsRepository = oAuthClientDetailsRepository;
  }

  public List<OAuthClientDetails> getClientDetails() {
    return (List<OAuthClientDetails>) oAuthClientDetailsRepository.findAll();
  }
}
