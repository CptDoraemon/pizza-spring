package com.xiaoxi.pizza.pizza.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DoughResponse {
  @NonNull
  private Long id;
  @NonNull
  private String name;
  @NonNull
  private BigDecimal price;
  @NonNull
  private Integer inventory;
}
