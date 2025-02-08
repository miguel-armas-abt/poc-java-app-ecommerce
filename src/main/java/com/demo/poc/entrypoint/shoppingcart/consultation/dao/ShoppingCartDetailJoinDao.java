package com.demo.poc.entrypoint.shoppingcart.consultation.dao;

import com.demo.poc.entrypoint.shoppingcart.consultation.entity.ShoppingCartDetailJoin;
import java.util.List;

public interface ShoppingCartDetailJoinDao {

  List<ShoppingCartDetailJoin> getShoppingCartDetailByClientDocumentNumber(String documentNumber);

}
