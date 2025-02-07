package com.demo.poc.entrypoint.shoppingcart.mapper;

import com.demo.poc.entrypoint.shoppingcart.entity.ClientEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClientMapper {

  public static ClientEntity toEntity(ResultSet result) throws SQLException {
    return ClientEntity.builder()
        .id(result.getLong("id"))
        .name(result.getString("name"))
        .documentNumber(result.getString("document_number"))
        .documentType(result.getString("document_type"))
        .build();
  }
}
