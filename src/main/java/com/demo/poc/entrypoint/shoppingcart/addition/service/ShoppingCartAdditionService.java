package com.demo.poc.entrypoint.shoppingcart.addition.service;

import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;

public interface ShoppingCartAdditionService {

  void addProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest);
}
