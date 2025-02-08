package com.demo.poc.entrypoint.shoppingcart.consultation.dao;

import com.demo.poc.entrypoint.shoppingcart.consultation.entity.ShoppingCartEntity;

public interface ShoppingCartDao {

  ShoppingCartEntity findShoppingCartByClientId(Long clientId);
}
