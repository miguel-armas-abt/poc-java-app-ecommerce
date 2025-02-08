package com.demo.poc.entrypoint.products.management.dto;

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
public class ProductSaveRequestDto implements Serializable {

  private String name;
  private int stock;
  private Double unitPrice;
  private String category;
  private String description;
}
