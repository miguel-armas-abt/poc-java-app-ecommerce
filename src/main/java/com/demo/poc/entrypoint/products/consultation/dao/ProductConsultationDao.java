package com.demo.poc.entrypoint.products.consultation.dao;

import com.demo.poc.entrypoint.products.consultation.entity.ProductEntity;
import java.util.List;

public interface ProductConsultationDao {

  List<ProductEntity> findAll();

  List<ProductEntity> findByCategory(String category);

  List<ProductEntity> findLessThanPrice(Double unitPrice);

  List<ProductEntity> findByNameMatch(String name);

  ProductEntity findById(Long id);
}
