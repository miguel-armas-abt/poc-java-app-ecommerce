package com.demo.poc.entrypoint.shoppingcart.finder.dao;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ClientEntity;

public interface ClientQueryDao {

  ClientEntity findByDocumentNumber(String documentNumber);
}
