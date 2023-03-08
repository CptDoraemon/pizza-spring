package com.xiaoxi.pizza.config.auth.exceptions;

public class EmailRegisteredException extends RuntimeException {
  public EmailRegisteredException(String email) {
    super("Email " + email + " is registered already");
  }
}
