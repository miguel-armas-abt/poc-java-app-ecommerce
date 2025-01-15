package com.demo.poc.dao;

import com.demo.poc.entity.SneakerEntity;
import java.util.List;

public interface SneakerDao {

  List<SneakerEntity> findAll();

  SneakerEntity findById(Long id);

  void save(SneakerEntity employee);

  void deleteById(Long id);
}
