package ru.relex.hotelteam.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.relex.hotelteam.security.model.LoginInfo;

/**
 * Отвечает за авторизацию пользователя при логине в систему
 */
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final ObjectMapper mapper = new ObjectMapper();

  public AuthenticationFilter(AuthenticationSuccessHandler successHandler,
      AuthenticationManager authManager) {

    super();
    this.setAllowSessionCreation(false); //в REST сессии не предусмотрены
    /*
    стандартный спринговый менеджер аутенфикации который под капотом
    использует всё что настроено в DaoAuthenticationProvider
     */
    this.setAuthenticationManager(authManager);
    this.setFilterProcessesUrl("/login"); // всё что не логин этим фильтром игнорируется
    this.setAuthenticationSuccessHandler(successHandler); // в случае успешной аутентификации будет вызнан этот хэндлер
  }

  @Override
  public Authentication attemptAuthentication(final HttpServletRequest request, final HttpServletResponse response) throws AuthenticationException {
    try {
      //самое важное в этом методе: прочитать пользователя из тела JSON
      var user = mapper.readValue(request.getReader(), LoginInfo.class);
      // и отдать эту информацию на дальнейшую аутентификацию
      return getAuthenticationManager().authenticate(
          new UsernamePasswordAuthenticationToken(user.getLogin(), user.getPassword()));
    } catch (IOException e) {
      throw new BadCredentialsException("Failed to parse user login info");
    }
  }

}
