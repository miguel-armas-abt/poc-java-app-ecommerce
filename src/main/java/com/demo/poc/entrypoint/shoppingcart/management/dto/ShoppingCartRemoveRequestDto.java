package com.demo.poc.entrypoint.shoppingcart.management.dto;

import lombok.*;

import java.io.Serializable;
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ShoppingCartRemoveRequestDto implements Serializable {

    private ClientDto client;
    private ProductDto product;


}
