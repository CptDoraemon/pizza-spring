package com.xiaoxi.pizza;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthenticationControllerTests.class)
public class AuthenticationControllerTests {
  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testHello() throws Exception {
//    mockMvc.perform(get("/api/v1/auth/hello"))
//        .andExpect(status().isOk())
//        .andExpect(content().json("{'message':  'ok'}"));
  }
}
