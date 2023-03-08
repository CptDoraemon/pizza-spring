package com.xiaoxi.pizza.entity.pizza;

import com.xiaoxi.pizza.entity.user.User;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Order {

  @ManyToOne
  OrderDough dough;

  @ManyToMany
  OrderTopping topping;

  @ManyToOne
  User user;

  @NotNull
  private BigDecimal price;

  @NotNull
  @Size(min = 1, max = 200)
  String address;

  @NotNull
  @Size(min = 1, max = 1000)
  String note;

  private OffsetDateTime createAt;

  private OffsetDateTime finishedAt;

  @PrePersist
  void placedAt() {
    createAt = OffsetDateTime.now();
  }
}
