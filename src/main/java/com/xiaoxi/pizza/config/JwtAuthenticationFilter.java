package com.xiaoxi.pizza.config;

import com.xiaoxi.pizza.entity.user.User;
import com.xiaoxi.pizza.entity.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response,
      @NonNull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }

    final String token = authHeader.substring(7);
    final String username = jwtService.extractUsername(token);
    Optional<User> optionalUser = userRepository.findByEmail(username);
    if (optionalUser.isEmpty()) {
      filterChain.doFilter(request, response);
      return;
    }

    User user = optionalUser.get();
    if (!jwtService.isTokenValid(token, user)) {
      filterChain.doFilter(request, response);
      return;
    }
    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
        user.getUsername(),
        null,
        user.getAuthorities()
    );
    authToken.setDetails(
        new WebAuthenticationDetailsSource().buildDetails(request)
    );
    SecurityContextHolder.getContext().setAuthentication(authToken);
    filterChain.doFilter(request, response);
  }
}
