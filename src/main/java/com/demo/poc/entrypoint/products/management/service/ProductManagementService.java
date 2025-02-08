package com.demo.poc.entrypoint.products.management.service;

import com.demo.poc.entrypoint.products.management.dto.ProductToSaveRequestDto;

public interface ProductManagementService {

  void save(ProductToSaveRequestDto product);

  void deleteById(Long id);

}
