package com.demo.poc.entrypoint.shoppingcart.addition.repository;

import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;

public interface ShoppingCartAdditionRepository {

  void addNewProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest);
}
