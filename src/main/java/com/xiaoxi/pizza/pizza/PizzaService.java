package com.xiaoxi.pizza.pizza;

import com.xiaoxi.pizza.pizza.entity.DoughRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PizzaService {
  private final DoughRepository doughRepo;
}
