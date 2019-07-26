package ru.relex.hotelteam.security.filter;

import io.jsonwebtoken.Jws;
import java.io.IOException;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.relex.hotelteam.security.service.ITokenService;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

  private static final String LOGIN_API = "/login/";
  private static final String REGISTRATION_API = "/users/";
  private static final String AUTH_HEADER = "Authorization";
  private static final String BEARER = "Bearer ";

  private final ITokenService tokenService;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      ITokenService tokenService) {
    super(authenticationManager);
    this.tokenService = tokenService;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String contextPath = request.getContextPath();
    String requestURI = request.getRequestURI();
    if ((contextPath + LOGIN_API).equalsIgnoreCase(requestURI) || (contextPath + REGISTRATION_API).equalsIgnoreCase(requestURI)) {
      chain.doFilter(request, response);
      return;
    }

    String token = request.getHeader(AUTH_HEADER);

    if (token == null || token.isBlank()) {
      throw new BadCredentialsException("Invalid auth token");
    }
    if (!token.startsWith(BEARER)) {
      throw new BadCredentialsException("Invalid auth token");
    }

    String jwtToken = token.replace(BEARER, "");
    var parsedToken = tokenService.parseJws(jwtToken);

    var authenticationToken = new UsernamePasswordAuthenticationToken(
        parsedToken.getBody().getSubject(), null, Set.of());
    SecurityContextHolder
        .getContext()
        .setAuthentication(authenticationToken);

    chain.doFilter(request,response);
  }
}
