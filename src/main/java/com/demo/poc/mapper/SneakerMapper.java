package com.demo.poc.mapper;

import com.demo.poc.dto.SneakerRequestDto;
import com.demo.poc.dto.SneakerResponseDto;
import com.demo.poc.entity.SneakerEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SneakerMapper {

  public static SneakerEntity toEntity(ResultSet result) throws SQLException {
    SneakerEntity employee = SneakerEntity.builder()
        .id(result.getLong("id"))
        .brand(result.getString("brand"))
        .price(result.getDouble("price"))
        .qualification(result.getInt("qualification"))
        .provider(result.getString("provider"))
        .gender(result.getString("gender"))
        .size(result.getDouble("size"))
        .type(result.getString("type"))
        .build();
    return employee;
  }

  public static SneakerEntity toEntity(SneakerRequestDto request) {
    return SneakerEntity.builder()
        .brand(request.getBrand())
        .price(request.getPrice())
        .qualification(request.getQualification())
        .provider(request.getProvider())
        .gender(request.getGender())
        .size(request.getSize())
        .type(request.getType())
        .build();
  }

  public static SneakerResponseDto toResponse(SneakerEntity entity) {
    SneakerResponseDto response = SneakerResponseDto.builder()
        .id(entity.getId())
        .brand(entity.getBrand())
        .price(entity.getPrice())
        .qualification(entity.getQualification())
        .provider(entity.getProvider())
        .gender(entity.getGender())
        .size(entity.getSize())
        .type(entity.getType())
        .build();
    return response;
  }

  public static List<SneakerResponseDto> toResponse(List<SneakerEntity> entities) {
    return entities.stream()
        .map(SneakerMapper::toResponse)
        .toList();
  }
}
