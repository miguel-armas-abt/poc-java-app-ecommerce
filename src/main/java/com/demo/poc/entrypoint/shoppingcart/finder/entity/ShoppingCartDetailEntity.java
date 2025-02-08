package com.demo.poc.entrypoint.shoppingcart.finder.entity;

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
public class ShoppingCartDetailEntity implements Serializable {

  private Long productId;
  private Long shoppingCartId;
  private int quantity;
}
