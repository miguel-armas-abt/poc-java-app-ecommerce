package com.demo.poc.entrypoint.products.service.impl;

import com.demo.poc.entrypoint.products.dao.ProductDao;
import com.demo.poc.entrypoint.products.dto.ProductResponseDto;
import com.demo.poc.entrypoint.products.mapper.ProductConsultationMapper;
import com.demo.poc.entrypoint.products.service.ProductConsultationService;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductConsultationServiceImpl implements ProductConsultationService {

  private final ProductDao productDao;

  @Inject
  public ProductConsultationServiceImpl(ProductDao productDao) {
    this.productDao = productDao;
  }

  @Override
  public List<ProductResponseDto> findAll() {
    return productDao.findAll()
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByCategory(String category) {
    return productDao.findByCategory(category)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findLessThanPrice(Double unitPrice) {
    return productDao.findLessThanPrice(unitPrice)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByNameMatch(String name) {
    return productDao.findByNameMatch(name)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public ProductResponseDto findById(Long id) {
    return Optional.ofNullable(productDao.findById(id))
        .map(ProductConsultationMapper::toResponseDto)
        .orElseThrow(() -> new NoSuchElementException("No such product with id " + id));
  }


}
