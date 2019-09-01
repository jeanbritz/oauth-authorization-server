package com.jeanbritz.oauth.handler;

import lombok.extern.slf4j.*;
import org.springframework.security.access.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.web.access.*;
import org.springframework.stereotype.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

@Component
@Slf4j
public class DefaultAccessDeniedHandler implements AccessDeniedHandler {
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

    Authentication auth
            = SecurityContextHolder.getContext().getAuthentication();

    if (auth != null) {
      log.info("User '" + auth.getName()
              + "' attempted to access the protected URL: "
              + request.getRequestURI());
    }

    response.sendRedirect(request.getContextPath() + "/403");


  }
}
