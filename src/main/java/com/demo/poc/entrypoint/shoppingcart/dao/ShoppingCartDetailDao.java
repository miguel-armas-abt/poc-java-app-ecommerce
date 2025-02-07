package com.demo.poc.entrypoint.shoppingcart.dao;

import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailEntity;
import java.util.List;

public interface ShoppingCartDetailDao {

  List<ShoppingCartDetailEntity> findShoppingCartDetailsByShoppingCardId(Long shoppingCartId);
}
