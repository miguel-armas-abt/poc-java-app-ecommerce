package com.demo.poc.entrypoint.shoppingcart.finder.repository.impl;

import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartDetailJoinQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.finder.mapper.ShoppingCartFinderMapper;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepository;
import com.google.inject.Inject;
import java.util.List;

public class ShoppingCartDetailJoinQueryRepositoryImpl implements ShoppingCartDetailFinderRepository {

  private final ShoppingCartDetailJoinQueryDao shoppingCartDetailJoinQueryDao;

  @Inject
  public ShoppingCartDetailJoinQueryRepositoryImpl(ShoppingCartDetailJoinQueryDao shoppingCartDetailJoinQueryDao) {
    this.shoppingCartDetailJoinQueryDao = shoppingCartDetailJoinQueryDao;
  }

  @Override
  public List<ShoppingCartDetailDto> findAsDtoByClientDocument(String documentNumber) {
    return shoppingCartDetailJoinQueryDao.findByClientDocument(documentNumber)
        .stream()
        .map(ShoppingCartFinderMapper::toShoppingCartDetailDto)
        .toList();
  }

  @Override
  public List<ShoppingCartDetailEntity> findAsEntityByClientDocument(String documentNumber) {
    return shoppingCartDetailJoinQueryDao.findByClientDocument(documentNumber)
        .stream()
        .map(ShoppingCartFinderMapper::toShoppingCartDetailEntity)
        .toList();
  }

  @Override
  public boolean supports(Class<?> selectedCass) {
    return this.getClass().isAssignableFrom(selectedCass);
  }
}