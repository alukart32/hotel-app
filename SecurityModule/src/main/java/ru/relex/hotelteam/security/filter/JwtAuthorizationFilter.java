package ru.relex.hotelteam.security.filter;

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
import org.springframework.security.core.GrantedAuthority;
import ru.relex.hotelteam.db.mapper.IUserMapper;
import ru.relex.hotelteam.security.mapstruct.IAuthorityMapstruct;
import ru.relex.hotelteam.security.service.ITokenService;
import ru.relex.hotelteam.security.utils.SecurityConstraints;
import ru.relex.hotelteam.shared.model.Authority;

public class JwtAuthorizationFilter extends AbstractJwtAuthorizationFilter {

  private final IUserMapper mapper;
  private final IAuthorityMapstruct mapstruct;

  public JwtAuthorizationFilter(AuthenticationManager authenticationManager,
      ITokenService tokenService, ObjectMapper mapper, IUserMapper mapper1,
      IAuthorityMapstruct mapstruct) {
    super(authenticationManager, tokenService, mapper);
    this.mapper = mapper1;
    this.mapstruct = mapstruct;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    try {
      String requestPath = getRelativePathFromRequest(request);
      if (isIgnoredPath(requestPath)) {
        chain.doFilter(request, response);
        return;
      }
      Jws<Claims> parsedToken = extractTokenFromRequest(request);

      String login = parsedToken.getBody().getSubject();

      assertThatNotRefreshToken(parsedToken);

      Authentication authentication = createAuthentication(login);

      onSuccessfulAuthentication(request, response, authentication);

      chain.doFilter(request, response);
    } catch (BadCredentialsException e) {
      onUnsuccessfulAuthentication(request, response, e);
    } catch (ExpiredJwtException | DecodingException | SignatureException e) {
      onUnsuccessfulAuthentication(request, response, new BadCredentialsException(e.getMessage()));
    }
  }

  private boolean isIgnoredPath(String requestPath) {
    return requestPath.equalsIgnoreCase(SecurityConstraints.REGISTRATION_API)
        || requestPath.equalsIgnoreCase((SecurityConstraints.LOGIN_API));
  }

  private Authentication createAuthentication(String login) {
    Authority authority = mapper.getAuthoritiesForUser(login);
    GrantedAuthority grantedAuthority = mapstruct.toGrantedAuthority(authority);
    return new UsernamePasswordAuthenticationToken(login, null, Set.of(grantedAuthority));
  }

  private void assertThatNotRefreshToken(Jws<Claims> parsedToken) {
    if (parsedToken.getBody().containsKey("refresh")) {
      throw new BadCredentialsException("Refresh token is not allowed here");
    }
  }
}
