package com.demo.poc.entrypoint.products.service;

import com.demo.poc.entrypoint.products.dto.ProductToSaveRequestDto;

public interface ProductManagementService {

  void save(ProductToSaveRequestDto product);

  void deleteById(Long id);

}
