package com.demo.poc.entrypoint.shoppingcart.mapper;

import com.demo.poc.entrypoint.products.dto.ProductResponseDto;
import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ShoppingCartConsultationMapper {

  public static ShoppingCartDetailDto toShoppingCartDetailDto(ProductResponseDto product, int quantity) {
    return ShoppingCartDetailDto.builder()
        .productId(product.getId())
        .productName(product.getName())
        .quantity(quantity)
        .unitPrice(product.getUnitPrice())
        .subtotal(product.getUnitPrice() * quantity)
        .build();
  }
}
