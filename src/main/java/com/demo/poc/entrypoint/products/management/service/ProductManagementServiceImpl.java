package com.demo.poc.entrypoint.products.management.service;

import com.demo.poc.entrypoint.products.consultation.entity.ProductEntity;
import com.demo.poc.entrypoint.products.management.dto.ProductToSaveRequestDto;
import com.demo.poc.entrypoint.products.management.mapper.ProductManagementMapper;
import com.demo.poc.entrypoint.products.management.dao.ProductManagementDao;
import com.google.inject.Inject;

public class ProductManagementServiceImpl implements ProductManagementService {

  private final ProductManagementDao managementDao;

  @Inject
  public ProductManagementServiceImpl(ProductManagementDao managementDao) {
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
