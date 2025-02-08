package com.demo.poc.entrypoint.shoppingcart.finder.service;

import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailResponseDto;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepositoryHelper;
import com.google.inject.Inject;
import java.util.ArrayList;

public class ShoppingCartFinderServiceImpl implements ShoppingCartFinderService {

  private final ShoppingCartDetailFinderRepositoryHelper shoppingCartRepository;

  @Inject
  public ShoppingCartFinderServiceImpl(ShoppingCartDetailFinderRepositoryHelper shoppingCartRepository) {
    this.shoppingCartRepository = shoppingCartRepository;
  }

  @Override
  public ShoppingCartDetailResponseDto retrieveShoppingCartDetailByClientDocumentNumber(String documentNumber) {
    ShoppingCartDetailResponseDto baseResponse = ShoppingCartDetailResponseDto.builder()
        .details(new ArrayList<>())
        .total(0.0)
        .build();

    return shoppingCartRepository.selectRepository()
        .findByClientDocument(documentNumber)
        .stream()
        .reduce(baseResponse, (modifiedResponse, detail) -> {
          modifiedResponse.getDetails().add(detail);
          modifiedResponse.setTotal(modifiedResponse.getTotal() + detail.getSubtotal());
          return modifiedResponse;
        }, (firstResponse, secondResponse) -> {
          firstResponse.getDetails().addAll(secondResponse.getDetails());
          firstResponse.setTotal(firstResponse.getTotal() + secondResponse.getTotal());
          return firstResponse;
        });
  }


}
