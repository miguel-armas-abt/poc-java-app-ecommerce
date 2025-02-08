package com.demo.poc.entrypoint.shoppingcart.addition.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;

public interface ShoppingCartDetailCommandDao {

  void addNewProductToShoppingCart(ShoppingCartDetailEntity shoppingCartDetail);
}
