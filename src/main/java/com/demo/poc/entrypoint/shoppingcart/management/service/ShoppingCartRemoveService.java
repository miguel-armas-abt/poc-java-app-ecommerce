package com.demo.poc.entrypoint.shoppingcart.management.service;

import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartRemoveRequestDto;

public interface ShoppingCartRemoveService {

    void removeProductToShoppingCart(ShoppingCartRemoveRequestDto shoppingCartRemoveRequestDto);
}
