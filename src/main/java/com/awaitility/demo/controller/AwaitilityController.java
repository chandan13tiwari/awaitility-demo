package com.awaitility.demo.controller;

import com.awaitility.demo.entity.Counter;
import com.awaitility.demo.service.CounterService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/v1/awaitility")
public class AwaitilityController {

  private static final Logger logger = getLogger(AwaitilityController.class);
  @Autowired
  CounterService counterService;

  @GetMapping("/get-count")
  public int getCountInfo(@RequestParam("id") UUID id) {
    return counterService.getCounts(id).getCount();
  }

  @PostMapping("/add-count")
  public Counter addCount() {
    return counterService.addCounts();
  }

  @PutMapping("/update-count")
  public Counter updateCount(@RequestParam("id") UUID id) {
    return counterService.updateCounts(id);
  }

  @GetMapping("/demov1")
  public String testAwaitilityAtMost() {
    UUID counterId = UUID.fromString("0a140152-2a74-4578-90e5-cc13871deee9");
    int expectedCount = 9;
    await("Testing counter with At Most").atMost(2, TimeUnit.MINUTES).until(() -> {
      int actualCount = counterService.getCounts(counterId).getCount();
      logger.info("Current value of counter is {}", actualCount);
      return expectedCount == actualCount;
    });

    return "Matched!!";
  }

  @GetMapping("/demov2")
  public String testAwaitilityAtLeast() {
    UUID counterId = UUID.fromString("0a140152-2a74-4578-90e5-cc13871deee9");
    int expectedCount = 9;
    await("Testing counter with At Least").atLeast(1, TimeUnit.MINUTES).and().atMost(2, TimeUnit.MINUTES)
        .until(() -> {
          int actualCount = counterService.getCounts(counterId).getCount();
          logger.info("Current value of counter is: {}", actualCount);
          return expectedCount == actualCount;
        });

    return "Matched!!";
  }

}
