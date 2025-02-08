package com.demo.poc.entrypoint.shoppingcart.addition.service;

import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepository;
import com.google.inject.Inject;

public class ShoppingCartAdditionServiceImpl implements ShoppingCartAdditionService {

  private final ShoppingCartDetailFinderRepository shoppingCartDetailFinderRepository;

  @Inject
  public ShoppingCartAdditionServiceImpl(ShoppingCartDetailFinderRepository shoppingCartDetailFinderRepository) {
    this.shoppingCartDetailFinderRepository = shoppingCartDetailFinderRepository;
  }

  @Override
  public void addProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest) {
    boolean productIsPresent = shoppingCartDetailFinderRepository
        .findByClientDocument(shoppingCartAdditionRequest.getClient().getDocumentNumber())
        .stream()
        .anyMatch(detail -> detail.getProductId().equals(shoppingCartAdditionRequest.getProduct().getId()));



  }
}
