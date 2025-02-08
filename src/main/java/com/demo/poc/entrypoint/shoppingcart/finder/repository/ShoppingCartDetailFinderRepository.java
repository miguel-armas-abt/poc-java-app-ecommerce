package com.demo.poc.entrypoint.shoppingcart.finder.repository;

import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import java.util.List;

public interface ShoppingCartDetailFinderRepository {

  List<ShoppingCartDetailDto> findAsDtoByClientDocument(String documentNumber);

  List<ShoppingCartDetailEntity> findAsEntityByClientDocument(String documentNumber);

  boolean supports(Class<?> selectedCass);

}
