package com.demo.poc.entrypoint.shoppingcart.addition.repository;

import com.demo.poc.entrypoint.shoppingcart.addition.dao.ShoppingCartDetailCommandDao;
import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.addition.mapper.ShoppingCartAdditionMapper;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ClientQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartQueryDao;
import com.google.inject.Inject;
import java.util.Optional;

public class ShoppingCartAdditionRepositoryImpl implements ShoppingCartAdditionRepository {

  private final ShoppingCartDetailCommandDao shoppingCartDetailCommandDao;
  private final ClientQueryDao clientQueryDao;
  private final ShoppingCartQueryDao shoppingCartQueryDao;

  @Inject
  public ShoppingCartAdditionRepositoryImpl(ShoppingCartDetailCommandDao shoppingCartDetailCommandDao,
                                            ClientQueryDao clientQueryDao,
                                            ShoppingCartQueryDao shoppingCartQueryDao) {
    this.shoppingCartDetailCommandDao = shoppingCartDetailCommandDao;
    this.clientQueryDao = clientQueryDao;
    this.shoppingCartQueryDao = shoppingCartQueryDao;
  }

  @Override
  public void addNewProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest) {
    Optional.ofNullable(clientQueryDao.findByDocumentNumber(shoppingCartAdditionRequest.getClient().getDocumentNumber()))
        .map(client -> shoppingCartQueryDao.findByClientId(client.getId()))
        .map(shoppingCart -> ShoppingCartAdditionMapper.toEntity(shoppingCartAdditionRequest, shoppingCart.getId()))
        .ifPresent(shoppingCartDetailCommandDao::addNewProductToShoppingCart);
  }


}
