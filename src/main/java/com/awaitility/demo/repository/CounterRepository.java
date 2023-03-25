package com.awaitility.demo.repository;

import com.awaitility.demo.entity.Counter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CounterRepository extends JpaRepository<Counter, UUID> {

}
