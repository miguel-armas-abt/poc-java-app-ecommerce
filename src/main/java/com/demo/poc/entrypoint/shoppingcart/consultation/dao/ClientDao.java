package com.demo.poc.entrypoint.shoppingcart.consultation.dao;

import com.demo.poc.entrypoint.shoppingcart.consultation.entity.ClientEntity;

public interface ClientDao {

  ClientEntity findByDocumentNumber(String documentNumber);
}
