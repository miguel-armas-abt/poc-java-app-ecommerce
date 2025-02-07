package com.demo.poc.entrypoint.shoppingcart.dao;

import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailJoin;
import java.util.List;

public interface ShoppingCartDetailJoinDao {

  List<ShoppingCartDetailJoin> getShoppingCartDetailByClientDocumentNumber(String documentNumber);

}
