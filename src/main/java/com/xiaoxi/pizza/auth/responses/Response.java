package com.xiaoxi.pizza.auth.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Response<T> {
  private final String message;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private final T payload;
}
