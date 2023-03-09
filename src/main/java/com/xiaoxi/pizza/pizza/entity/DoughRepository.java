package com.xiaoxi.pizza.pizza.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoughRepository extends JpaRepository<Dough, Long> {

  Page<Dough> findByIsActiveTrue(Pageable pageable);

}
