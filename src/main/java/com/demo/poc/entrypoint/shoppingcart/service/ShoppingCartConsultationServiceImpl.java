package com.demo.poc.entrypoint.shoppingcart.service;

import com.demo.poc.entrypoint.products.service.ProductConsultationService;
import com.demo.poc.entrypoint.shoppingcart.dao.ClientDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDetailDao;
import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailResponseDto;
import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.mapper.ShoppingCartConsultationMapper;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShoppingCartConsultationServiceImpl implements ShoppingCartConsultationService {

  private final ClientDao clientDao;
  private final ShoppingCartDao shoppingCartDao;
  private final ShoppingCartDetailDao shoppingCartDetailDao;
  private final ProductConsultationService productConsultationService;

  @Inject
  public ShoppingCartConsultationServiceImpl(ClientDao clientDao,
                                             ShoppingCartDao shoppingCartDao,
                                             ShoppingCartDetailDao shoppingCartDetailDao,
                                             ProductConsultationService productConsultationService) {
    this.clientDao = clientDao;
    this.shoppingCartDao = shoppingCartDao;
    this.shoppingCartDetailDao = shoppingCartDetailDao;
    this.productConsultationService = productConsultationService;
  }

  @Override
  public ShoppingCartDetailResponseDto retrieveShoppingCartDetailByClientDocumentNumber(String documentNumber) {
    ShoppingCartDetailResponseDto baseResponse = ShoppingCartDetailResponseDto.builder()
        .details(new ArrayList<>())
        .total(0.0)
        .build();

    return joinAndGetShoppingCartByClientDocumentNumber(documentNumber)
        .stream()
        .map(detailEntity -> Optional.ofNullable(productConsultationService.findById(detailEntity.getProductId()))
            .map(product -> ShoppingCartConsultationMapper.toShoppingCartDetailDto(product, detailEntity.getQuantity()))
            .orElseThrow(() -> new NoSuchElementException("No such product with id " + detailEntity.getShoppingCartId()))
        )
        .reduce(baseResponse, (currentResponse, detail) -> {
          currentResponse.getDetails().add(detail);
          currentResponse.setTotal(currentResponse.getTotal() + detail.getSubtotal());
          return currentResponse;
        }, (firstResponse, secondResponse) -> {
          firstResponse.getDetails().addAll(secondResponse.getDetails());
          firstResponse.setTotal(firstResponse.getTotal() + secondResponse.getTotal());
          return firstResponse;
        });
  }

  private List<ShoppingCartDetailEntity> joinAndGetShoppingCartByClientDocumentNumber(String documentNumber) {
    return Optional.ofNullable(clientDao.findByDocumentNumber(documentNumber))
        .map(client -> shoppingCartDao.findShoppingCartByClientId(client.getId()))
        .map(shoppingCart -> shoppingCartDetailDao.findShoppingCartDetailByShoppingCardId(shoppingCart.getId()))
        .orElseThrow(() -> new NoSuchElementException("No such shopping cart for client with document number " + documentNumber));
  }
}
