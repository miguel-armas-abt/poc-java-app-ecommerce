package com.demo.poc.entrypoint.products.consultation.service;

import com.demo.poc.entrypoint.products.consultation.dao.ProductConsultationDao;
import com.demo.poc.entrypoint.products.consultation.dto.ProductResponseDto;
import com.demo.poc.entrypoint.products.consultation.mapper.ProductConsultationMapper;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductConsultationServiceImpl implements ProductConsultationService {

  private final ProductConsultationDao productConsultationDao;

  @Inject
  public ProductConsultationServiceImpl(ProductConsultationDao productConsultationDao) {
    this.productConsultationDao = productConsultationDao;
  }

  @Override
  public List<ProductResponseDto> findAll() {
    return productConsultationDao.findAll()
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByCategory(String category) {
    return productConsultationDao.findByCategory(category)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findLessThanPrice(Double unitPrice) {
    return productConsultationDao.findLessThanPrice(unitPrice)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByNameMatch(String name) {
    return productConsultationDao.findByNameMatch(name)
        .stream()
        .map(ProductConsultationMapper::toResponseDto)
        .toList();
  }

  @Override
  public ProductResponseDto findById(Long id) {
    return Optional.ofNullable(productConsultationDao.findById(id))
        .map(ProductConsultationMapper::toResponseDto)
        .orElseThrow(() -> new NoSuchElementException("No such product with id " + id));
  }


}
