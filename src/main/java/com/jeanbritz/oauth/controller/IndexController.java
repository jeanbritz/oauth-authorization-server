package com.jeanbritz.oauth.controller;

import com.jeanbritz.oauth.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.approval.*;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import java.security.*;
import java.util.*;

@Controller
public class IndexController {

  private final ClientDetailsService clientDetailsService;
  private final ApprovalStore approvalStore;
  private TokenStore tokenStore;
  private final CommonService commonService;

  @Autowired
  public IndexController(ClientDetailsService clientDetailsService, ApprovalStore approvalStore,
                         TokenStore tokenStore, CommonService commonService) {
    this.clientDetailsService = clientDetailsService;
    this.approvalStore = approvalStore;
    this.tokenStore = tokenStore;
    this.commonService = commonService;
  }

  @GetMapping("/")
  public ModelAndView index(Map<String, Object> model, @AuthenticationPrincipal Principal principal) {
    List<Approval> approvals = null;

//      JdbcClientDetailsService jdbcClientDetailsService = (JdbcClientDetailsService) clientDetailsService;
//      approvals =
//              jdbcClientDetailsService.listClientDetails().stream()
//                      .map(clientDetails -> approvalStore.getApprovals(principal.getName(),
//                              clientDetails.getClientId()))
//                      .flatMap(Collection::stream)
//                      .collect(Collectors.toList());
//
//    model.put("approvals", approvals);
    model.put("clientDetails", commonService.getClientDetails());

    return new ModelAndView("index", model);
  }

  @GetMapping("/login")
  public ModelAndView login(Map<String, Object> model) {
    return new ModelAndView("login", model);
  }
}
