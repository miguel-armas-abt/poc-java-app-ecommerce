package com.demo.poc.entrypoint.shoppingcart.repository.impl;

import com.demo.poc.entrypoint.products.service.ProductConsultationService;
import com.demo.poc.entrypoint.shoppingcart.dao.ClientDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDetailDao;
import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailDto;
import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.mapper.ShoppingCartConsultationMapper;
import com.demo.poc.entrypoint.shoppingcart.repository.ShoppingCartRepository;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShoppingCartCompositeRepositoryImpl implements ShoppingCartRepository {

  private final ClientDao clientDao;
  private final ShoppingCartDao shoppingCartDao;
  private final ShoppingCartDetailDao shoppingCartDetailDao;
  private final ProductConsultationService productConsultationService;

  @Inject
  public ShoppingCartCompositeRepositoryImpl(ClientDao clientDao,
                                             ShoppingCartDao shoppingCartDao,
                                             ShoppingCartDetailDao shoppingCartDetailDao,
                                             ProductConsultationService productConsultationService) {
    this.clientDao = clientDao;
    this.shoppingCartDao = shoppingCartDao;
    this.shoppingCartDetailDao = shoppingCartDetailDao;
    this.productConsultationService = productConsultationService;
  }

  @Override
  public List<ShoppingCartDetailDto> getShoppingCartDetailByClientDocumentNumber(String documentNumber) {
    return joinAndGetShoppingCartByClientDocumentNumber(documentNumber)
        .stream()
        .map(detailEntity -> Optional.ofNullable(productConsultationService.findById(detailEntity.getProductId()))
            .map(product -> ShoppingCartConsultationMapper.toShoppingCartDetailDto(product, detailEntity.getQuantity()))
            .orElseThrow(() -> new NoSuchElementException("No such product with id " + detailEntity.getShoppingCartId()))
        )
        .toList();
  }

  private List<ShoppingCartDetailEntity> joinAndGetShoppingCartByClientDocumentNumber(String documentNumber) {
    return Optional.ofNullable(clientDao.findByDocumentNumber(documentNumber))
        .map(client -> shoppingCartDao.findShoppingCartByClientId(client.getId()))
        .map(shoppingCart -> shoppingCartDetailDao.findShoppingCartDetailsByShoppingCardId(shoppingCart.getId()))
        .orElseThrow(() -> new NoSuchElementException("No such shopping cart for client with document number " + documentNumber));
  }

  @Override
  public boolean supports(Class<?> selectedCass) {
    return this.getClass().isAssignableFrom(selectedCass);
  }
}
