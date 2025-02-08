package com.demo.poc.entrypoint.products.consultation.service;

import com.demo.poc.entrypoint.products.consultation.dto.ProductResponseDto;
import java.util.List;

public interface ProductConsultationService {

  List<ProductResponseDto> findAll();

  List<ProductResponseDto> findByCategory(String category);

  List<ProductResponseDto> findLessThanPrice(Double unitPrice);

  List<ProductResponseDto> findByNameMatch(String name);

  ProductResponseDto findById(Long id);
}
