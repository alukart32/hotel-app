package ru.relex.hotelteam.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


/**
 * Отвечает за конфигурацию security вашего приложения. Не забывайте, что класс должен быть помечен как {@link
 * Configuration}.
 * <p/>
 * Аннотация {@link EnableWebSecurity} указывет на то что конфигурация отвечает за сетевую конфигурацию, другими словами
 * ваш класс реализовывает {@link WebSecurityConfigurerAdapter} и настраивает фильтры для обработки запросов.
 * <p/>
 * Аннотация {@link EnableGlobalMethodSecurity} говорит о том, что security вашего приложения будет конфигурироваться
 * через аннотации методов. Для того чтобы оно правильно заработало нужно хотя бы один из параметров:
 * <ul>
 * <li/> {@link EnableGlobalMethodSecurity#jsr250Enabled()} <li/> {@link EnableGlobalMethodSecurity#securedEnabled()}
 * <li/> {@link EnableGlobalMethodSecurity#prePostEnabled()}
 * </ul>
 * Выставить в значение {@literal true}. В зависимости от параметров выставленных в {@literal true} появится возможность
 * использовать одну из следующих аннотаций для проверки прав
 * <ul>
 * <li/>{@link javax.annotation.security.RolesAllowed}, {@link javax.annotation.security.DenyAll} или {@link
 * javax.annotation.security.PermitAll} из <b>javax.annotation:javax.annotation-api</b> <li/>{@link
 * org.springframework.security.access.annotation.Secured} из <b>org.springframework.security:spring-security-core</b>
 * <li/>{@link org.springframework.security.access.prepost.PreAuthorize}, {@link org.springframework.security.access.prepost.PostAuthorize}
 * оттуда же.
 * </ul>
 * Второй и третий варианты более гибкие, первый более простой.
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(jsr250Enabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;
  private final AuthenticationSuccessHandler successHandler;

  public SecurityConfig(UserDetailsService userDetailsService,
      AuthenticationSuccessHandler successHandler) {
    this.userDetailsService = userDetailsService;
    this.successHandler = successHandler;
  }

  /**
   * Конфигурирует сам процесс аутентификации и авторизации пользователя: то как шифруется пароль, то откуда берётся
   * пользователь для проверки и т.д.
   */
  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(userDetailsService);
    auth.authenticationProvider(provider);
    super.configure(auth);
  }

  /**
   * @return класс, который используется для шифрования пароля и для сверки пароля при авторизации. Самые
   * распространённые варианты:
   * <ul>
   * <li/>{@link Pbkdf2PasswordEncoder} - преобразует пароль в SHA-хеш <li/>{@link
   * org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder} - использующий bCrypt алгоритм
   * </ul>
   */
  @Bean
  PasswordEncoder passwordEncoder() {
    var pe = new Pbkdf2PasswordEncoder("", 185000, 512);
    pe.setEncodeHashAsBase64(true);
    return pe;
  }

  /**
   * Конфигурирует обработку HTTP запросов. То есть та конфигурация, которая будет сделана в этом методе будет
   * использоваться для обработки <i>каждого</i> запроса. При этом сначала выполняются действия, которые описаны в этой
   * конфигурации, а только потом обрабатываются аннотации, из {@link EnableGlobalMethodSecurity}.
   */
  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    var filter = new ShowcaseAuthFilter(successHandler, authenticationManager());
    http
        .csrf().disable()// отключаем csrf и cors - они нам на текущем этапе не нужны
        .cors().disable()
        .authorizeRequests() // настраиваем авторизацию
        .antMatchers("/login").permitAll() // на адрес /login может отправить запрос любой пользователь.
        //Еще можно писать, например "/auth/**" - что значит что вообще любой запрос будет обрабатываться данным блоком
        .anyRequest().authenticated() //все остальные (что не описаны выше) запросы должны быть аутентифицированы
        .and() // затем настраиваем непосредственно фильтры
        /*
          .formLogin()
          .loginProcessingUrl(Urls.LOGIN_URL)
          .successHandler(successHandler)
          .failureHandler(failureHandler)
        .and()
         */
        .addFilter(filter) // добавляем фильтр который будет авторизировать пользователя в окне логина
        .addFilterBefore(new JwtAccessFilter(),
            ShowcaseAuthFilter.class)
    ;

  }
}
