package com.demo.poc.entrypoint.products.management.dao;

import com.demo.poc.entrypoint.products.consultation.entity.ProductEntity;

public interface ProductManagementDao {

  void save(ProductEntity product);

  void deleteById(Long id);
}
