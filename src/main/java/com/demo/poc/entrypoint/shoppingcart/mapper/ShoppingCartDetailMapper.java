package com.demo.poc.entrypoint.shoppingcart.mapper;

import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailEntity;
import java.sql.ResultSet;
import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCartDetailMapper {

  public static ShoppingCartDetailEntity toEntity(ResultSet result) throws SQLException {
    return ShoppingCartDetailEntity.builder()
        .productId(result.getLong("product_id"))
        .shoppingCartId(result.getLong("shopping_cart_id"))
        .quantity(result.getInt("quantity"))
        .build();
  }
}
