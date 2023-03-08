package com.xiaoxi.pizza.config.auth.dto;

import com.xiaoxi.pizza.entity.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoResponse {
  private String email;
  private String firstname;
  private String lastname;
  private Role role;
}
