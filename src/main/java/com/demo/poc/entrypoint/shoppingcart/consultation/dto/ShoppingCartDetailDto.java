package com.demo.poc.entrypoint.shoppingcart.consultation.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDetailDto implements Serializable {

  private Long productId;
  private String productName;
  private int quantity;
  private Double unitPrice;
  private Double subtotal;
}
