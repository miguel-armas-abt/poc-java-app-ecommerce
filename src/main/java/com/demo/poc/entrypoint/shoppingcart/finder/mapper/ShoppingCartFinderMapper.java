package com.demo.poc.entrypoint.shoppingcart.finder.mapper;

import com.demo.poc.entrypoint.products.finder.dto.ProductResponseDto;
import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailJoin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCartFinderMapper {

  public static ShoppingCartDetailDto toShoppingCartDetailDto(ProductResponseDto product, int quantity) {
    return ShoppingCartDetailDto.builder()
        .productId(product.getId())
        .productName(product.getName())
        .quantity(quantity)
        .unitPrice(product.getUnitPrice())
        .subtotal(product.getUnitPrice() * quantity)
        .build();
  }

  public static ShoppingCartDetailDto toShoppingCartDetailDto(ShoppingCartDetailJoin detail) {
    return ShoppingCartDetailDto.builder()
        .productId(detail.getProductId())
        .productName(detail.getProductName())
        .quantity(detail.getQuantity())
        .unitPrice(detail.getUnitPrice())
        .subtotal(detail.getUnitPrice() * detail.getQuantity())
        .build();
  }

  public static ShoppingCartDetailEntity toShoppingCartDetailEntity(ShoppingCartDetailJoin detail) {
    return ShoppingCartDetailEntity.builder()
        .productId(detail.getProductId())
        .quantity(detail.getQuantity())
        .shoppingCartId(detail.getShoppingCartId())
        .build();
  }
}
