package com.demo.poc.entrypoint.shoppingcart.finder.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartEntity;

public interface ShoppingCartQueryDao {

  ShoppingCartEntity findByClientId(Long clientId);
}
