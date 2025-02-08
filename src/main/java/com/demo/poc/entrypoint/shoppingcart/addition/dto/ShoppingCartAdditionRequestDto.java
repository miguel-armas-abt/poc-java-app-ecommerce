package com.demo.poc.entrypoint.shoppingcart.addition.dto;

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
public class ShoppingCartAdditionRequestDto implements Serializable {

  private String documentNumber;
  private Long productId;
  private int quantity;
}
