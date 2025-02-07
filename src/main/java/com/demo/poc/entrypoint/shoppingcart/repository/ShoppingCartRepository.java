package com.demo.poc.entrypoint.shoppingcart.repository;

import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailDto;
import java.util.List;

public interface ShoppingCartRepository {

  List<ShoppingCartDetailDto> getShoppingCartDetailByClientDocumentNumber(String documentNumber);

  boolean supports(Class<?> selectedCass);

}
