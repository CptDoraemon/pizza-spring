package com.xiaoxi.pizza.pizza.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Topping {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Enumerated(EnumType.STRING)
  @NotNull
  private ToppingType type;

  @NotNull
  @Size(min = 1, max = 100)
  private String name;

  @NotNull
  private BigDecimal price;

  @NotNull
  private Integer inventory;

  @NotNull
  private Boolean isActive;
}
