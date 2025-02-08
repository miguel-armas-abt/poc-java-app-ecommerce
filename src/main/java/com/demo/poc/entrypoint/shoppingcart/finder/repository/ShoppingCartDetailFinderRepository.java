package com.demo.poc.entrypoint.shoppingcart.finder.repository;

import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailDto;
import java.util.List;

public interface ShoppingCartDetailFinderRepository {

  List<ShoppingCartDetailDto> findByClientDocument(String documentNumber);

  boolean supports(Class<?> selectedCass);

}
