package com.demo.poc.entrypoint.shoppingcart.repository.impl;

import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDetailJoinDao;
import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.mapper.ShoppingCartConsultationMapper;
import com.demo.poc.entrypoint.shoppingcart.repository.ShoppingCartRepository;
import com.google.inject.Inject;
import java.util.List;

public class ShoppingCartJoinRepositoryImpl implements ShoppingCartRepository {

  private final ShoppingCartDetailJoinDao shoppingCartDetailJoinDao;

  @Inject
  public ShoppingCartJoinRepositoryImpl(ShoppingCartDetailJoinDao shoppingCartDetailJoinDao) {
    this.shoppingCartDetailJoinDao = shoppingCartDetailJoinDao;
  }

  @Override
  public List<ShoppingCartDetailDto> getShoppingCartDetailByClientDocumentNumber(String documentNumber) {
    return shoppingCartDetailJoinDao.getShoppingCartDetailByClientDocumentNumber(documentNumber)
        .stream()
        .map(ShoppingCartConsultationMapper::toShoppingCartDetailDto)
        .toList();
  }

  @Override
  public boolean supports(Class<?> selectedCass) {
    return this.getClass().isAssignableFrom(selectedCass);
  }
}