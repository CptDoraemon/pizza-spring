package com.xiaoxi.pizza.auth;

import com.xiaoxi.pizza.auth.entity.UserRepository;
import com.xiaoxi.pizza.auth.exceptions.EmailRegisteredException;
import com.xiaoxi.pizza.auth.entity.Role;
import com.xiaoxi.pizza.auth.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

  private final UserRepository userRepo;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  private User addUser(
      String email,
      String firstname,
      String lastname,
      String password,
      Role role
  ) throws EmailRegisteredException {
    Optional<User> existingUser = userRepo.findByEmail(email.toLowerCase());
    if (existingUser.isPresent()) {
      throw new EmailRegisteredException(email);
    }

    String encodedPassword = passwordEncoder.encode(password);
    User user = User.builder()
        .email(email)
        .firstname(firstname)
        .lastname(lastname)
        .password(encodedPassword)
        .role(role)
        .build();
    userRepo.save(user);
    return user;
  }

  public User createUser(
      String email,
      String firstname,
      String lastname,
      String password
  ) throws EmailRegisteredException {
    return addUser(email, firstname, lastname, password, Role.USER);
  }

  public User createAdmin(
      String email,
      String firstname,
      String lastname,
      String password
  ) throws EmailRegisteredException {
    return addUser(email, firstname, lastname, password, Role.ADMIN);
  }
}
