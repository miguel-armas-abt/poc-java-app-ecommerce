package com.demo.poc.entrypoint.shoppingcart.finder.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import java.util.List;

public interface ShoppingCartDetailQueryDao {

  List<ShoppingCartDetailEntity> findByShoppingCardId(Long shoppingCartId);
}
