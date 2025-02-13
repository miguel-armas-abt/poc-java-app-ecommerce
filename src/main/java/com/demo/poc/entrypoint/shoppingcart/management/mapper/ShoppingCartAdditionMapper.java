package com.demo.poc.entrypoint.shoppingcart.management.mapper;

import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCartAdditionMapper {

  public static ShoppingCartDetailEntity toEntity(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest,
                                                  Long shoppingCartId) {
    return ShoppingCartDetailEntity.builder()
        .productId(shoppingCartAdditionRequest.getProduct().getId())
        .quantity(shoppingCartAdditionRequest.getProduct().getQuantity())
        .shoppingCartId(shoppingCartId)
        .build();
  }
}
