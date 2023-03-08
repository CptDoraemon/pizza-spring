package com.xiaoxi.pizza.config;

import com.xiaoxi.pizza.config.auth.AuthenticationService;
import com.xiaoxi.pizza.entity.pizza.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements ApplicationRunner {

  private final AuthenticationService authService;
  private final DoughRepository doughRepo;
  private final ToppingRepository toppingRepo;

  public void run(ApplicationArguments args) {
    try {
      // account
      authService.createAdmin("test@test.com", "Xiaoxi", "Yu", "12345678");

      // dough
      doughRepo.save(
          Dough
              .builder()
              .name("Medium 12″ Round")
              .price(new BigDecimal("2.99"))
              .isActive(true)
              .inventory(10)
              .build()
      );
      doughRepo.save(
          Dough
              .builder()
              .name("Medium 12″ Stuffed Crust Round")
              .price(new BigDecimal("3.49"))
              .isActive(true)
              .inventory(10)
              .build()
      );
      doughRepo.save(
          Dough
              .builder()
              .name("Large 14″ Round")
              .price(new BigDecimal("3.99"))
              .isActive(true)
              .inventory(10)
              .build()
      );
      doughRepo.save(
          Dough
              .builder()
              .name("Large 14″ Stuffed Crust Round")
              .price(new BigDecimal("4.49"))
              .isActive(true)
              .inventory(10)
              .build()
      );

      // toppings

      // cheese
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.CHEESE)
              .name("Cheddar")
              .price(new BigDecimal("0.99"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.CHEESE)
              .name("Mozzarella")
              .price(new BigDecimal("1.29"))
              .isActive(true)
              .inventory(20)
              .build()
      );

      // meat
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.MEAT)
              .name("Pepperoni")
              .price(new BigDecimal("0.99"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.MEAT)
              .name("Italian Sausage")
              .price(new BigDecimal("1.29"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.MEAT)
              .name("Bacon")
              .price(new BigDecimal("1.29"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.MEAT)
              .name("Ham")
              .price(new BigDecimal("0.99"))
              .isActive(true)
              .inventory(20)
              .build()
      );

      // vegetables
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.VEGETABLE)
              .name("Fresh Mushrooms")
              .price(new BigDecimal("0.99"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.VEGETABLE)
              .name("Tomatoes")
              .price(new BigDecimal("0.79"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.VEGETABLE)
              .name("Pineapple")
              .price(new BigDecimal("0.49"))
              .isActive(true)
              .inventory(20)
              .build()
      );
      toppingRepo.save(
          Topping
              .builder()
              .type(ToppingType.VEGETABLE)
              .name("Jalapeño Peppers")
              .price(new BigDecimal("0.79"))
              .isActive(true)
              .inventory(20)
              .build()
      );
    } catch (Exception e) {
      StringWriter stringWriter = new StringWriter();
      PrintWriter printWriter = new PrintWriter(stringWriter);
      e.printStackTrace(printWriter);
      String stackTrace = stringWriter.toString();

      log.error("DataLoader error: " + e.getMessage() + stackTrace);
    }
  }
}