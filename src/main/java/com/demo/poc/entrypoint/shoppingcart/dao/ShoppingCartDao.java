package com.demo.poc.entrypoint.shoppingcart.dao;

import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartEntity;

public interface ShoppingCartDao {

  ShoppingCartEntity findShoppingCartByClientId(Long clientId);
}
