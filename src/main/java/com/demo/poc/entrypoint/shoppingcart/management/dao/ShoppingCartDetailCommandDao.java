package com.demo.poc.entrypoint.shoppingcart.management.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;

public interface ShoppingCartDetailCommandDao {

  void removeProductToShoppingCart(ShoppingCartDetailEntity shoppingCartDetail);

  void addNewProductToShoppingCart(ShoppingCartDetailEntity shoppingCartDetail);

  void updateProduct(ShoppingCartDetailEntity shoppingCartDetail);



}
