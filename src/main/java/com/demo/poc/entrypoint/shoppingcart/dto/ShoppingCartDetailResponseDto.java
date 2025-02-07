package com.demo.poc.entrypoint.shoppingcart.dto;

import java.io.Serializable;
import java.util.List;
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
public class ShoppingCartDetailResponseDto implements Serializable {

  private List<ShoppingCartDetailDto> details;

  private Double total;
}