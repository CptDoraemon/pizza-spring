package com.xiaoxi.pizza.controller;

import com.xiaoxi.pizza.config.auth.responses.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class CustomControllerAdvice {

  @ExceptionHandler(Exception.class) // exception handled
  public ResponseEntity<Response<String>> handleExceptions(
      Exception e
  ) {
    StringWriter stringWriter = new StringWriter();
    PrintWriter printWriter = new PrintWriter(stringWriter);
    e.printStackTrace(printWriter);
    String stackTrace = stringWriter.toString();
    log.warn("Fall through error: " + e.getMessage() + ", " + stackTrace);

    return new ResponseEntity<>(
        new Response<>("Server Error", null),
        HttpStatus.INTERNAL_SERVER_ERROR
    );
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Response<Map<String, String>>> handleValidationExceptions(
      MethodArgumentNotValidException e
  ) {
    Map<String, String> errors = new HashMap<>();

    e.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });

    Response<Map<String, String>> res = new Response<>("Validation Error", errors);
    return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
  }

}
