package com.demo.poc.entrypoint.shoppingcart.finder.repository.impl;

import com.demo.poc.entrypoint.products.finder.service.ProductFinderService;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ClientQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartDetailQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.finder.mapper.ShoppingCartFinderMapper;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepository;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShoppingCartDetailCompositeRepositoryImpl implements ShoppingCartDetailFinderRepository {

  private final ClientQueryDao clientQueryDao;
  private final ShoppingCartQueryDao shoppingCartQueryDao;
  private final ShoppingCartDetailQueryDao shoppingCartDetailQueryDao;
  private final ProductFinderService productFinderService;

  @Inject
  public ShoppingCartDetailCompositeRepositoryImpl(ClientQueryDao clientQueryDao,
                                                   ShoppingCartQueryDao shoppingCartQueryDao,
                                                   ShoppingCartDetailQueryDao shoppingCartDetailQueryDao,
                                                   ProductFinderService productFinderService) {
    this.clientQueryDao = clientQueryDao;
    this.shoppingCartQueryDao = shoppingCartQueryDao;
    this.shoppingCartDetailQueryDao = shoppingCartDetailQueryDao;
    this.productFinderService = productFinderService;
  }

  @Override
  public List<ShoppingCartDetailDto> findAsDtoByClientDocument(String documentNumber) {
    return findAsEntityByClientDocument(documentNumber)
        .stream()
        .map(detailEntity -> Optional.ofNullable(productFinderService.findById(detailEntity.getProductId()))
            .map(product -> ShoppingCartFinderMapper.toShoppingCartDetailDto(product, detailEntity.getQuantity()))
            .orElseThrow(() -> new NoSuchElementException("No such product with id " + detailEntity.getShoppingCartId()))
        )
        .toList();
  }

  @Override
  public List<ShoppingCartDetailEntity> findAsEntityByClientDocument(String documentNumber) {
    return Optional.ofNullable(clientQueryDao.findByDocumentNumber(documentNumber))
        .map(client -> shoppingCartQueryDao.findByClientId(client.getId()))
        .map(shoppingCart -> shoppingCartDetailQueryDao.findByShoppingCardId(shoppingCart.getId()))
        .orElseThrow(() -> new NoSuchElementException("No such shopping cart for client with document number " + documentNumber));
  }

  @Override
  public boolean supports(Class<?> selectedCass) {
    return this.getClass().isAssignableFrom(selectedCass);
  }
}
