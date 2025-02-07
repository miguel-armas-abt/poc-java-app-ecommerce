package com.demo.poc.entrypoint.shoppingcart.entity;

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
public class ShoppingCartEntity implements Serializable {

  private Long id;
  private Long clientId;
}
