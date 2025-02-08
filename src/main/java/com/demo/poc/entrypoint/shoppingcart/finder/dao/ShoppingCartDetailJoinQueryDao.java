package com.demo.poc.entrypoint.shoppingcart.finder.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailJoin;
import java.util.List;

public interface ShoppingCartDetailJoinQueryDao {

  List<ShoppingCartDetailJoin> findByClientDocument(String documentNumber);
}
