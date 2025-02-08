package com.demo.poc.entrypoint.shoppingcart.consultation.dao;

import com.demo.poc.entrypoint.shoppingcart.consultation.entity.ShoppingCartDetailEntity;
import java.util.List;

public interface ShoppingCartDetailDao {

  List<ShoppingCartDetailEntity> findShoppingCartDetailsByShoppingCardId(Long shoppingCartId);
}
