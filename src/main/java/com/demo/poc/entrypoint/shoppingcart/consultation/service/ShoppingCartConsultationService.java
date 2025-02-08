package com.demo.poc.entrypoint.shoppingcart.consultation.service;

import com.demo.poc.entrypoint.shoppingcart.consultation.dto.ShoppingCartDetailResponseDto;

public interface ShoppingCartConsultationService {

  ShoppingCartDetailResponseDto retrieveShoppingCartDetailByClientDocumentNumber(String documentNumber);
}
