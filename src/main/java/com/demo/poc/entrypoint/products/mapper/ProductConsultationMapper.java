package com.demo.poc.entrypoint.products.mapper;

import com.demo.poc.entrypoint.products.dto.ProductResponseDto;
import com.demo.poc.entrypoint.products.entity.ProductEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductConsultationMapper {

  public static ProductEntity toEntity(ResultSet result) throws SQLException {
    return ProductEntity.builder()
        .id(result.getLong("id"))
        .name(result.getString("name"))
        .stock(result.getInt("stock"))
        .unitPrice(result.getDouble("price"))
        .category(result.getString("category"))
        .description(result.getString("description"))
        .enabled(result.getBoolean("enabled"))
        .build();
  }

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
