package com.demo.poc.entrypoint.products.dao;

import com.demo.poc.entrypoint.products.entity.ProductEntity;
import java.util.List;

public interface ProductDao {

  List<ProductEntity> findAll();

  List<ProductEntity> findByCategory(String category);

  List<ProductEntity> findLessThanPrice(Double unitPrice);

  List<ProductEntity> findByNameMatch(String name);

  ProductEntity findById(Long id);

  void save(ProductEntity product);

  void deleteById(Long id);
}
