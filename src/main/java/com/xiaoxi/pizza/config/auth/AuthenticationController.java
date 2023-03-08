package com.xiaoxi.pizza.config.auth;

import com.xiaoxi.pizza.config.JwtService;
import com.xiaoxi.pizza.config.auth.dto.LoginRequest;
import com.xiaoxi.pizza.config.auth.dto.SignUpRequest;
import com.xiaoxi.pizza.config.auth.dto.UserInfoResponse;
import com.xiaoxi.pizza.config.auth.exceptions.EmailRegisteredException;
import com.xiaoxi.pizza.config.auth.responses.Response;
import com.xiaoxi.pizza.entity.user.User;
import com.xiaoxi.pizza.entity.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final AuthenticationService authService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/signUp")
  public ResponseEntity<Response<Object>> signUp(
      @Valid @RequestBody SignUpRequest request
  ) {
    try {
      authService.createUser(
          request.getEmail(),
          request.getFirstname(),
          request.getLastname(),
          request.getPassword()
      );
      return new ResponseEntity<>(new Response<>("User registered", null), HttpStatus.CREATED);
    } catch (EmailRegisteredException e) {
      return new ResponseEntity<>(new Response<>("Email Registered", null), HttpStatus.BAD_REQUEST);
    }
  }

  @GetMapping("/hello")
  public ResponseEntity<Response<Object>> hello() {
    return new ResponseEntity<>(new Response<>("ok", null), HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<Response<Object>> authenticate(@RequestBody LoginRequest request) {
    try {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
      User user = (User) authentication.getPrincipal();
      String token = jwtService.generateToken(user);
      Map<String, String> payload = new HashMap<>();
      payload.put("token", token);

      return new ResponseEntity<>(new Response<>("Success", payload), HttpStatus.OK);
    } catch (DisabledException e) {
      return new ResponseEntity<>(new Response<>("User Disabled", null), HttpStatus.FORBIDDEN);
    } catch (LockedException e) {
      return new ResponseEntity<>(new Response<>("User Locked", null), HttpStatus.FORBIDDEN);
    } catch (BadCredentialsException e) {
      return new ResponseEntity<>(new Response<>("Bad Credential", null), HttpStatus.FORBIDDEN);
    }
  }

  @GetMapping("/userinfo")
  public ResponseEntity<Response<UserInfoResponse>> authenticate(Authentication authentication) {
    log.warn("userinfo");
    User user = (User) authentication.getPrincipal();
    UserInfoResponse res = UserInfoResponse.builder()
        .email(user.getEmail())
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .role(user.getRole())
        .build();
    return new ResponseEntity<>(new Response<>("message", res), HttpStatus.OK);
  }

}
