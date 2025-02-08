package com.demo.poc.entrypoint.products.consultation.mapper;

import com.demo.poc.entrypoint.products.consultation.dto.ProductResponseDto;
import com.demo.poc.entrypoint.products.consultation.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConsultationMapper {

  public static ProductResponseDto toResponseDto(ProductEntity entity) {
    return ProductResponseDto.builder()
        .id(entity.getId())
        .name(entity.getName())
        .stock(entity.getStock())
        .unitPrice(entity.getUnitPrice())
        .category(entity.getCategory())
        .description(entity.getDescription())
        .build();
  }
}
