package com.demo.poc.entrypoint.shoppingcart.management.service;

import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartAdditionRequestDto;

public interface ShoppingCartAdditionService {

  void addProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest);


}
