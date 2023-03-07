package com.xiaoxi.pizza.controller.auth.exceptions;

public class EmailRegisteredException extends RuntimeException {
  public EmailRegisteredException(String email) {
    super("Email " + email + " is registered already");
  }
}
