package com.xiaoxi.pizza.pizza;

import com.xiaoxi.pizza.auth.entity.Role;
import com.xiaoxi.pizza.auth.entity.User;
import com.xiaoxi.pizza.auth.responses.Response;
import com.xiaoxi.pizza.pizza.dto.DoughAdminResponse;
import com.xiaoxi.pizza.pizza.dto.DoughResponse;
import com.xiaoxi.pizza.pizza.entity.Dough;
import com.xiaoxi.pizza.pizza.entity.DoughRepository;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pizza")
@RequiredArgsConstructor
@Slf4j
public class PizzaController {

  private final DoughRepository doughRepo;

  @GetMapping("/doughs")
  ResponseEntity<Response<Page<Dough>>> getAllDoughs(
      Authentication authentication,
      @PageableDefault(size = 20, sort = "id") Pageable pageable
  ) {
    boolean isAdmin = false;

    Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
    for (GrantedAuthority authority : authorities) {
      if (authority.getAuthority().equals(Role.ADMIN.name())) {
        isAdmin = true;
        break;
      }
    }

    Page<Dough> doughsPage = isAdmin ?
        doughRepo.findByIsActiveTrue(pageable) :
        doughRepo.findAll(pageable);
    return new ResponseEntity<>(new Response<>(null, doughsPage), HttpStatus.OK);
  }
}
