package com.demo.poc.entrypoint.shoppingcart.management.mapper;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartRemoveRequestDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCarRemoveMapper {

    public static ShoppingCartDetailEntity toEntity(ShoppingCartRemoveRequestDto shoppingCartRemovaRequestDto,
                                                    Long shoppingCartId) {
        return ShoppingCartDetailEntity.builder()
                .productId(shoppingCartRemovaRequestDto.getProduct().getId())
                .quantity(shoppingCartRemovaRequestDto.getProduct().getQuantity())
                .shoppingCartId(shoppingCartId)
                .build();
    }
}
