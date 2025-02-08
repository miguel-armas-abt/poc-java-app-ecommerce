package com.demo.poc.entrypoint.shoppingcart.consultation.mapper;

import com.demo.poc.entrypoint.products.consultation.dto.ProductResponseDto;
import com.demo.poc.entrypoint.shoppingcart.consultation.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.consultation.entity.ShoppingCartDetailJoin;
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

  public static ShoppingCartDetailDto toShoppingCartDetailDto(ShoppingCartDetailJoin detail) {
    return ShoppingCartDetailDto.builder()
        .productId(detail.getProductId())
        .productName(detail.getProductName())
        .quantity(detail.getQuantity())
        .unitPrice(detail.getUnitPrice())
        .subtotal(detail.getUnitPrice() * detail.getQuantity())
        .build();
  }
}
