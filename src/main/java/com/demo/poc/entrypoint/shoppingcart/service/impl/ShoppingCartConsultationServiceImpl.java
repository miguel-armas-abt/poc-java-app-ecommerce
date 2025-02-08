package com.demo.poc.entrypoint.shoppingcart.service.impl;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailResponseDto;
import com.demo.poc.entrypoint.shoppingcart.repository.ShoppingCartRepository;
import com.demo.poc.entrypoint.shoppingcart.service.ShoppingCartConsultationService;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

public class ShoppingCartConsultationServiceImpl implements ShoppingCartConsultationService {

  private final Set<ShoppingCartRepository> shoppingCartRepositorySet;

  @Inject
  public ShoppingCartConsultationServiceImpl(Set<ShoppingCartRepository> shoppingCartRepositorySet) {
    this.shoppingCartRepositorySet = shoppingCartRepositorySet;
  }

  @Override
  public ShoppingCartDetailResponseDto retrieveShoppingCartDetailByClientDocumentNumber(String documentNumber) {
    ShoppingCartDetailResponseDto baseResponse = ShoppingCartDetailResponseDto.builder()
        .details(new ArrayList<>())
        .total(0.0)
        .build();

    return selectRepository()
        .getShoppingCartDetailByClientDocumentNumber(documentNumber)
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

  private ShoppingCartRepository selectRepository() {
    Class<?> selectedClass = PropertiesReader.getPropertyClass("shopping-cart.repository.selector-class");

    return shoppingCartRepositorySet.stream()
        .filter(repository -> repository.supports(selectedClass))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("No such selector class"));
  }
}
