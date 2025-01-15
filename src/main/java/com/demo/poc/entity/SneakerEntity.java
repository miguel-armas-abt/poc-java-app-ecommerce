package com.demo.poc.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SneakerEntity implements Serializable {

  private Long id;
  private String brand;
  private Double price;
  private int qualification;
  private String provider;
  private String gender;
  private Double size;
  private String type;
}