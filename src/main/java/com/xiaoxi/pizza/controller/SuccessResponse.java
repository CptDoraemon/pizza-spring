package com.xiaoxi.pizza.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class SuccessResponse<T> {
  private final String status = "ok";
  private final String message;
  private final T payload;
}
