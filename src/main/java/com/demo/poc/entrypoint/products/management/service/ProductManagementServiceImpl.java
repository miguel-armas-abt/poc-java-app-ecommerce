package com.demo.poc.entrypoint.products.management.service;

import com.demo.poc.entrypoint.products.finder.entity.ProductEntity;
import com.demo.poc.entrypoint.products.management.dto.ProductSaveRequestDto;
import com.demo.poc.entrypoint.products.management.mapper.ProductManagementMapper;
import com.demo.poc.entrypoint.products.management.dao.ProductCommandDao;
import com.google.inject.Inject;

public class ProductManagementServiceImpl implements ProductManagementService {

  private final ProductCommandDao managementDao;

  @Inject
  public ProductManagementServiceImpl(ProductCommandDao managementDao) {
    this.managementDao = managementDao;
  }

  @Override
  public void save(ProductSaveRequestDto product) {
    ProductEntity entity = ProductManagementMapper.toEntityWithoutId(product);
    managementDao.save(entity);
  }

  @Override
  public void deleteById(Long id) {
    managementDao.deleteById(id);
  }
}
