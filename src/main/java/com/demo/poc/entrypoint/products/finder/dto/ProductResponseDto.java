package com.demo.poc.entrypoint.products.finder.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDto implements Serializable {

  private Long id;
  private String name;
  private int stock;
  private Double unitPrice;
  private String category;
  private String description;
}