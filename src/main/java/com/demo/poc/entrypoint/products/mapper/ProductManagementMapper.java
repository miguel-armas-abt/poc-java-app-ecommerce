package com.demo.poc.entrypoint.products.mapper;

import com.demo.poc.entrypoint.products.dto.ProductToSaveRequestDto;
import com.demo.poc.entrypoint.products.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductManagementMapper {

  public static ProductEntity toEntityWithoutId(ProductToSaveRequestDto product) {
    return ProductEntity.builder()
        .name(product.getName())
        .stock(product.getStock())
        .unitPrice(product.getUnitPrice())
        .category(product.getCategory())
        .description(product.getDescription())
        .build();
  }
}
