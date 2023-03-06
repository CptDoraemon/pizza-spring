package com.xiaoxi.pizza.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse<T> {
  private final String status = "error";
  private final String message;
  private final T payload;
}
