package com.xiaoxi.pizza.controller.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
  @NotNull
  @Size(min = 1, max = 100)
  private String firstname;

  @NotNull
  @Size(min = 1, max = 100)
  private String lastname;

  @Email
  @NotNull
  private String email;

  @NotNull
  @Size(min = 8, max = 20)
  private String password;
}
