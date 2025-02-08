package com.demo.poc.entrypoint.products.management.service;

import com.demo.poc.entrypoint.products.management.dto.ProductSaveRequestDto;

public interface ProductManagementService {

  void save(ProductSaveRequestDto product);

  void deleteById(Long id);

}
