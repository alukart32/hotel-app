package ru.relex.hotelteam.security.filter;

import javax.servlet.*;
import java.io.IOException;

public class  JwtAccessFilter implements Filter {
  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
    chain.doFilter(request, response);
  }
}
