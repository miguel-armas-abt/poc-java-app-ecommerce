package com.demo.poc.entrypoint.products.management.dao;

import com.demo.poc.entrypoint.products.finder.entity.ProductEntity;

public interface ProductCommandDao {

  void save(ProductEntity product);

  void deleteById(Long id);
}
