package com.demo.poc.entrypoint.products.finder.dao;

import com.demo.poc.entrypoint.products.finder.entity.ProductEntity;
import java.util.List;

public interface ProductQueryDao {

  List<ProductEntity> findAll();

  List<ProductEntity> findByCategory(String category);

  List<ProductEntity> findLessThanPrice(Double unitPrice);

  List<ProductEntity> findByNameMatch(String name);

  ProductEntity findById(Long id);
}
