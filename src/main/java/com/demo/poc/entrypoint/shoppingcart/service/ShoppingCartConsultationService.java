package com.demo.poc.entrypoint.shoppingcart.service;

import com.demo.poc.entrypoint.shoppingcart.dto.ShoppingCartDetailResponseDto;

public interface ShoppingCartConsultationService {

  ShoppingCartDetailResponseDto retrieveShoppingCartDetailByClientDocumentNumber(String documentNumber);
}
