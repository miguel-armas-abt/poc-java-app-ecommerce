package com.demo.poc.service;

import com.demo.poc.dao.SneakerDao;
import com.demo.poc.dto.SneakerRequestDto;
import com.demo.poc.dto.SneakerResponseDto;
import com.demo.poc.entity.SneakerEntity;
import com.demo.poc.mapper.SneakerMapper;
import com.google.inject.Inject;
import java.util.List;
import java.util.Map;

public class SneakerServiceImpl implements SneakerService {

  private final SneakerDao sneakerDao;

  @Inject
  public SneakerServiceImpl(SneakerDao sneakerDao) {
    this.sneakerDao = sneakerDao;
  }

  @Override
  public List<SneakerResponseDto> findAll() {
    List<SneakerEntity> entities = sneakerDao.findAll();
    List<SneakerResponseDto> sneakerList = SneakerMapper.toResponse(entities);
    return sneakerList;
  }

  @Override
  public SneakerResponseDto findById(Long id) {
    SneakerEntity entity = sneakerDao.findById(id);
    SneakerResponseDto sneaker = SneakerMapper.toResponse(entity);
    return sneaker;
  }

  @Override
  public void deleteById(Long id) {
    sneakerDao.deleteById(id);
  }

  @Override
  public void save(SneakerRequestDto sneakerRequest) {
    SneakerEntity entity = SneakerMapper.toEntity(sneakerRequest);
    sneakerDao.save(entity);
  }

  @Override
  public List<SneakerResponseDto> findByQueryParam(Map<String, String> queryParam) {
    return findAll()
        .stream()
        .filter(sneaker -> queryParam.containsKey("provider") ? queryParam.get("provider").equals(sneaker.getProvider()) : true)
        .filter(sneaker -> queryParam.containsKey("gender") ? queryParam.get("gender").equals(sneaker.getGender()) : true)
        .toList();
  }
}
