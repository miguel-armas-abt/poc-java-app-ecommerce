package com.demo.poc.entrypoint.products.finder.entity;

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
public class ProductEntity implements Serializable {

  private Long id;
  private String name;
  private int stock;
  private Double unitPrice;
  private String category;
  private String description;
  private boolean enabled;
}
