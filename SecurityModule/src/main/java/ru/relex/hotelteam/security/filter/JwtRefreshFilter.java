package ru.relex.hotelteam.security.filter;

import static ru.relex.hotelteam.security.utils.SecurityConstraints.REFRESH_API;

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
import org.springframework.security.core.Authentication;
import ru.relex.hotelteam.security.service.ITokenService;

public class JwtRefreshFilter extends AbstractJwtAuthorizationFilter {

  private static String[] acceptablePaths = {
      REFRESH_API
  };

  public JwtRefreshFilter(AuthenticationManager authenticationManager,
      ITokenService tokenService, ObjectMapper mapper) {
    super(authenticationManager, tokenService, mapper);
    setFilterProcessingUrls(acceptablePaths);
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    String relativePath = getRelativePathFromRequest(request);
    if (isNotProcessingPath(relativePath)) {
      chain.doFilter(request, response);
      return;
    }
    try {
      Jws<Claims> parsedToken = extractTokenFromRequest(request);
      assertThatInstanceOfRefreshToken(parsedToken);

      var authenticationToken = new UsernamePasswordAuthenticationToken(
          parsedToken.getBody().getSubject(), null, Set.of());
      onSuccessfulAuthentication(request, response, authenticationToken);
    } catch (BadCredentialsException e) {
      onUnsuccessfulAuthentication(request, response, e);
    } catch (ExpiredJwtException | DecodingException | SignatureException e) {
      onUnsuccessfulAuthentication(request, response, new BadCredentialsException(e.getMessage()));
    }
  }

  @Override
  protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
      Authentication authResult) throws IOException {
    Object username = authResult.getPrincipal();
    if (username instanceof String) {
      var tokenPair = tokenService.generateToken((String) username);
      writeInResponse(response, tokenPair);
    } else {
      throw new BadCredentialsException("Can`t generate new tokens. Invalid subject in token");
    }
  }

  private void assertThatInstanceOfRefreshToken(Jws<Claims> parsedToken) {
    if (!parsedToken.getBody().containsKey("refresh")) {
      throw new BadCredentialsException("Invalid auth token");
    }
  }

}
