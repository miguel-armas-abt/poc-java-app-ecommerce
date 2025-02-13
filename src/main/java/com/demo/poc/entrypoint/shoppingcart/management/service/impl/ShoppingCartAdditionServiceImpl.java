package com.demo.poc.entrypoint.shoppingcart.management.service.impl;

import com.demo.poc.entrypoint.shoppingcart.management.dao.ShoppingCartDetailCommandDao;
import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.management.mapper.ShoppingCartAdditionMapper;
import com.demo.poc.entrypoint.shoppingcart.management.service.ShoppingCartAdditionService;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepositoryHelper;
import com.google.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShoppingCartAdditionServiceImpl implements ShoppingCartAdditionService {

  private final ShoppingCartDetailFinderRepositoryHelper shoppingCartDetailFinderRepository;
  private final ShoppingCartDetailCommandDao shoppingCartDetailCommandDao;

  @Inject
  public ShoppingCartAdditionServiceImpl(ShoppingCartDetailFinderRepositoryHelper shoppingCartDetailFinderRepository,
                                         ShoppingCartDetailCommandDao shoppingCartDetailCommandDao) {
    this.shoppingCartDetailFinderRepository = shoppingCartDetailFinderRepository;
    this.shoppingCartDetailCommandDao = shoppingCartDetailCommandDao;
  }

  @Override
  public void addProductToShoppingCart(ShoppingCartAdditionRequestDto shoppingCartAdditionRequest) {
    List<ShoppingCartDetailEntity> shoppingCartDetailList = shoppingCartDetailFinderRepository
        .selectRepository()
        .findAsEntityByClientDocument(shoppingCartAdditionRequest.getClient().getDocumentNumber());

    Long shoppingCartId = Optional.of(shoppingCartDetailList)
        .filter(details -> !details.isEmpty())
        .map(details -> details.get(0).getShoppingCartId())
        .orElseThrow(() -> new NoSuchElementException("No such any shoppingCartId"));

    Optional<ShoppingCartDetailEntity> shoppingCartDetailOptional = shoppingCartDetailList
        .stream()
        .filter(detail -> detail.getProductId().equals(shoppingCartAdditionRequest.getProduct().getId()))
        .findFirst();

    shoppingCartDetailOptional
        .ifPresentOrElse(detail -> {
              int currentQuantity = detail.getQuantity();
              detail.setQuantity(currentQuantity + shoppingCartAdditionRequest.getProduct().getQuantity());
              shoppingCartDetailCommandDao.updateProduct(detail);
            },
            () -> {
              shoppingCartDetailCommandDao.addNewProductToShoppingCart(ShoppingCartAdditionMapper.toEntity(shoppingCartAdditionRequest, shoppingCartId));
            });
  }


}
