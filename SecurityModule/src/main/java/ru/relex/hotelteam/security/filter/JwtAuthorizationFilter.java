package ru.relex.hotelteam.security.filter;

import static ru.relex.hotelteam.security.utils.SecurityConstraints.LOGIN_API;
import static ru.relex.hotelteam.security.utils.SecurityConstraints.REGISTRATION_API;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.io.DecodingException;
import io.jsonwebtoken.security.SignatureException;
import java.io.IOException;
import java.util.Set;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import ru.relex.hotelteam.security.service.ITokenService;

public class JwtAuthorizationFilter extends AbstractJwtAuthorizationFilter {

  private static String[] ignoredPaths = {
      LOGIN_API,
      REGISTRATION_API
  };

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      ITokenService tokenService, ObjectMapper mapper) {
    super(authenticationManager, tokenService, mapper);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    try {
      Jws<Claims> parsedToken = extractTokenFromRequest(request);

      assertThatNotRefreshToken(parsedToken);

      var authenticationToken = new UsernamePasswordAuthenticationToken(
          parsedToken.getBody().getSubject(), null, Set.of());

      onSuccessfulAuthentication(request, response, authenticationToken);
      chain.doFilter(request, response);
    } catch (BadCredentialsException e) {
      onUnsuccessfulAuthentication(request, response, e);
    } catch (ExpiredJwtException | DecodingException | SignatureException e) {
      onUnsuccessfulAuthentication(request, response, new BadCredentialsException(e.getMessage()));
    }
  }

  private void assertThatNotRefreshToken(Jws<Claims> parsedToken) {
    if (parsedToken.getBody().containsKey("refresh")) {
      throw new BadCredentialsException("Refresh token is not allowed here");
    }
  }
}
