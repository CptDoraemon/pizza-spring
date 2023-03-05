package com.xiaoxi.pizza.auth;

import com.xiaoxi.pizza.auth.dto.*;
import com.xiaoxi.pizza.config.JwtService;
import com.xiaoxi.pizza.user.Role;
import com.xiaoxi.pizza.user.User;
import com.xiaoxi.pizza.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final UserRepository userRepo;
  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/signUp")
  public ResponseEntity<String> signUp(
      @Valid @RequestBody SignUpRequest request
  ) {
    try {
      String encodedPassword = passwordEncoder.encode(request.getPassword());

      User user = User.builder()
          .email(request.getEmail())
          .firstname(request.getFirstname())
          .lastname(request.getLastname())
          .password(encodedPassword)
          .role(Role.USER)
          .build();
      userRepo.save(user);
      return ResponseEntity.ok().build();
    } catch (Exception e) {
      System.out.println(e);
      return ResponseEntity.badRequest().build();
    }
    // TODO
  }

  @GetMapping("/hello")
  public ResponseEntity<String> hello() {
    System.out.println("hello");
    return ResponseEntity.ok("ok");
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Map<String, String> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    System.out.println(errors);
    return errors;
  }
//
//  @PostMapping("/login")
//  public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginRequest request) {
//    try {
//      authenticationManager.authenticate(
//          new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//      );
//      String token = jwtService.generateToken(request.)
//    } catch (AuthenticationException) {
//
//    }
//  }
//
//  @GetMapping("/userinfo")
//  public ResponseEntity<UserInfoResponse> authenticate(Authentication authentication) {
//    // TODO
//    User user = (User) authentication.getPrincipal();
//  }

}
