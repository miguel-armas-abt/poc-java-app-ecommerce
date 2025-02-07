package com.demo.poc.entrypoint.shoppingcart.dao;

import com.demo.poc.entrypoint.shoppingcart.entity.ClientEntity;

public interface ClientDao {

  ClientEntity findByDocumentNumber(String documentNumber);
}
