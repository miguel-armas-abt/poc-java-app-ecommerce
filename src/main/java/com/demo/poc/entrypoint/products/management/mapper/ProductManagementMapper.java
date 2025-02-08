package com.demo.poc.entrypoint.products.management.mapper;

import com.demo.poc.entrypoint.products.management.dto.ProductSaveRequestDto;
import com.demo.poc.entrypoint.products.finder.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductManagementMapper {

  public static ProductEntity toEntityWithoutId(ProductSaveRequestDto product) {
    return ProductEntity.builder()
        .name(product.getName())
        .stock(product.getStock())
        .unitPrice(product.getUnitPrice())
        .category(product.getCategory())
        .description(product.getDescription())
        .build();
  }
}
