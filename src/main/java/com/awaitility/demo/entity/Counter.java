package com.awaitility.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "countr")
public class Counter {
  @Id
  @Column(name = "cntr_id", nullable = false)
  // @GeneratedValue(generator = "UUID")
  // @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private UUID counterId;

  @Column(name = "cntr_count")
  private int count;

}
