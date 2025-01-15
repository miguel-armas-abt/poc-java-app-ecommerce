package com.demo.poc.service;

import com.demo.poc.dto.SneakerRequestDto;
import com.demo.poc.dto.SneakerResponseDto;
import java.util.List;

public interface SneakerService {

  List<SneakerResponseDto> findAll();

  SneakerResponseDto findById(Long id);

  void deleteById(Long id);

  void save(SneakerRequestDto sneakerRequest);
}
