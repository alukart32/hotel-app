package ru.relex.hotelteam.security.filter;

import static ru.relex.hotelteam.security.utils.SecurityConstraints.AUTH_HEADER;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.BEARER;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.relex.hotelteam.security.service.ITokenService;

class AbstractJwtAuthorizationFilter extends BasicAuthenticationFilter {

  protected ObjectMapper mapper;
  protected ITokenService tokenService;


  private List<String> filterProcessingUrls;

  public AbstractJwtAuthorizationFilter(
      AuthenticationManager authenticationManager, ITokenService tokenService,
      ObjectMapper mapper) {
    super(authenticationManager);
    this.tokenService = tokenService;
    this.mapper = mapper;
  }

  Jws<Claims> extractTokenFromRequest(HttpServletRequest request) throws BadCredentialsException {
    String token = request.getHeader(AUTH_HEADER);

    if (token == null || token.isBlank()) {
      throw new BadCredentialsException("Invalid auth token");
    }
    if (!token.startsWith(BEARER)) {
      throw new BadCredentialsException("Invalid auth token");
    }

    String jwtToken = token.replace(BEARER, "");
    return tokenService.parseJws(jwtToken);
  }

  protected void writeInResponse(HttpServletResponse resp, Object payload) throws IOException {
    try (var writer = resp.getWriter()) {
      mapper.writeValue(writer, payload);
    }
  }

  @Override
  protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      Authentication authResult) throws IOException {
    SecurityContextHolder.getContext().setAuthentication(authResult);
  }

  @Override
  protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException failed) throws IOException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    writeInResponse(response, failed.getMessage());
  }

  protected void setFilterProcessingUrls(String[] paths) {
    this.filterProcessingUrls = List.of(paths);
  }

  protected boolean isNotProcessingPath(String path) {
    return filterProcessingUrls.stream().noneMatch(
        (acceptedPath) -> acceptedPath.equalsIgnoreCase(path)
    );
  }

  protected String getRelativePathFromRequest(HttpServletRequest request) {
    return request.getRequestURI().replace(request.getContextPath(), "");
  }
}

