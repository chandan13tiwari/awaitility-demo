package com.awaitility.demo.service;

import com.awaitility.demo.entity.Counter;
import com.awaitility.demo.repository.CounterRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class CounterService {

  private static final Logger logger = getLogger(CounterService.class);
  @Autowired
  private CounterRepository counterRepository;

  public Counter getCounts(UUID counterId) {
    Optional<Counter> counter = counterRepository.findById(counterId);
    return counter.orElseGet(() -> Counter.builder().build());
  }

  public Counter addCounts() {
    UUID countId = UUID.randomUUID();
    Counter counter = Counter.builder().counterId(countId).count(1).build();
    return counterRepository.save(counter);
  }

  public Counter updateCounts(UUID counterId) {
    Counter existingCounter = getCounts(counterId);
    Counter counter = null;

    if (existingCounter.getCounterId() != null) {
      counter = Counter.builder().counterId(existingCounter.getCounterId()).count(existingCounter.getCount() + 1)
          .build();
    } else {
      logger.error("Counter ID {} not found!!", counterId);
    }

    return counterRepository.save(counter);
  }
}
