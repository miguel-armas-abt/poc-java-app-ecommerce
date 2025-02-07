package com.demo.poc.entrypoint.shoppingcart.mapper;

import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCartMapper {

  public static ShoppingCartEntity toEntity(ResultSet result) throws SQLException {
    return ShoppingCartEntity.builder()
        .id(result.getLong("id"))
        .clientId(result.getLong("client_id"))
        .build();
  }
}
