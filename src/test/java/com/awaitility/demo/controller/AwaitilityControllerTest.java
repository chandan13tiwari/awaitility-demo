package com.awaitility.demo.controller;

import com.awaitility.demo.entity.Counter;
import com.awaitility.demo.service.CounterService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest
class AwaitilityControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  CounterService counterService;

  private static final String GET_API = "/v1/awaitility/get-count";
  private static final String POST_API = "/v1/awaitility/add-count";
  private static final String PUT_API = "/v1/awaitility/update-count";

  @Test
  void testGetCounts() throws Exception {
    UUID id = UUID.randomUUID();
    Counter counter = Counter.builder().counterId(id).count(1).build();
    when(counterService.getCounts(Mockito.any())).thenReturn(counter);

    mockMvc.perform(MockMvcRequestBuilders.get(GET_API).contentType("*/*").param("id", id.toString()))
        .andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print());
  }

}
