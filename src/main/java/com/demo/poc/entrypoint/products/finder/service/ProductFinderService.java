package com.demo.poc.entrypoint.products.finder.service;

import com.demo.poc.entrypoint.products.finder.dto.ProductResponseDto;
import java.util.List;

public interface ProductFinderService {

  List<ProductResponseDto> findAll();

  List<ProductResponseDto> findByCategory(String category);

  List<ProductResponseDto> findLessThanPrice(Double unitPrice);

  List<ProductResponseDto> findByNameMatch(String name);

  ProductResponseDto findById(Long id);
}
