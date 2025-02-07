package com.demo.poc.entrypoint.products.service.impl;

import com.demo.poc.entrypoint.products.dao.ProductDao;
import com.demo.poc.entrypoint.products.entity.ProductEntity;
import com.demo.poc.entrypoint.products.dto.ProductToSaveRequestDto;
import com.demo.poc.entrypoint.products.mapper.ProductManagementMapper;
import com.demo.poc.entrypoint.products.service.ProductManagementService;
import com.google.inject.Inject;

public class ProductManagementServiceImpl implements ProductManagementService {

  private final ProductDao managementDao;

  @Inject
  public ProductManagementServiceImpl(ProductDao managementDao) {
    this.managementDao = managementDao;
  }

  @Override
  public void save(ProductToSaveRequestDto product) {
    ProductEntity entity = ProductManagementMapper.toEntityWithoutId(product);
    managementDao.save(entity);
  }

  @Override
  public void deleteById(Long id) {
    managementDao.deleteById(id);
  }
}
