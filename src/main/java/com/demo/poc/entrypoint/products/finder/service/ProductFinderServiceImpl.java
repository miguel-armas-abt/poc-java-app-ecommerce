package com.demo.poc.entrypoint.products.finder.service;

import com.demo.poc.entrypoint.products.finder.dao.ProductQueryDao;
import com.demo.poc.entrypoint.products.finder.dto.ProductResponseDto;
import com.demo.poc.entrypoint.products.finder.mapper.ProductFinderMapper;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ProductFinderServiceImpl implements ProductFinderService {

  private final ProductQueryDao productQueryDao;

  @Inject
  public ProductFinderServiceImpl(ProductQueryDao productQueryDao) {
    this.productQueryDao = productQueryDao;
  }

  @Override
  public List<ProductResponseDto> findAll() {
    return productQueryDao.findAll()
        .stream()
        .map(ProductFinderMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByCategory(String category) {
    return productQueryDao.findByCategory(category)
        .stream()
        .map(ProductFinderMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findLessThanPrice(Double unitPrice) {
    return productQueryDao.findLessThanPrice(unitPrice)
        .stream()
        .map(ProductFinderMapper::toResponseDto)
        .toList();
  }

  @Override
  public List<ProductResponseDto> findByNameMatch(String name) {
    return productQueryDao.findByNameMatch(name)
        .stream()
        .map(ProductFinderMapper::toResponseDto)
        .toList();
  }

  @Override
  public ProductResponseDto findById(Long id) {
    return Optional.ofNullable(productQueryDao.findById(id))
        .map(ProductFinderMapper::toResponseDto)
        .orElseThrow(() -> new NoSuchElementException("No such product with id " + id));
  }


}
