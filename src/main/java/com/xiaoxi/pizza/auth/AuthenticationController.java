package com.xiaoxi.pizza.auth;

import com.xiaoxi.pizza.auth.dto.*;
import com.xiaoxi.pizza.user.User;
import com.xiaoxi.pizza.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authService;
  private final UserRepository userRepo;
  private final AuthenticationManager authenticationManager;

  @PostMapping("/signUp")
  public ResponseEntity<SignUpResponse> register(@RequestBody SignUpRequest request) {
    // TODO
    return ResponseEntity.ok();
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

  }

  @GetMapping("/userinfo")
  public ResponseEntity<UserInfoResponse> authenticate(Authentication authentication) {
    // TODO
    User user = (User) authentication.getPrincipal();
  }

}
