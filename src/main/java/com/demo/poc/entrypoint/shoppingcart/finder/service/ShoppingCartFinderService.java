package com.demo.poc.entrypoint.shoppingcart.finder.service;

import com.demo.poc.entrypoint.shoppingcart.finder.dto.ShoppingCartDetailResponseDto;

public interface ShoppingCartFinderService {

  ShoppingCartDetailResponseDto retrieveDetailByClientDocument(String documentNumber);
}
