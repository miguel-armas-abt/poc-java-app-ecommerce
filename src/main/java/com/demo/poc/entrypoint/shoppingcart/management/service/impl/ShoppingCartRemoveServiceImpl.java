package com.demo.poc.entrypoint.shoppingcart.management.service.impl;

import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepositoryHelper;
import com.demo.poc.entrypoint.shoppingcart.management.dao.ShoppingCartDetailCommandDao;
import com.demo.poc.entrypoint.shoppingcart.management.dto.ShoppingCartRemoveRequestDto;
import com.demo.poc.entrypoint.shoppingcart.management.mapper.ShoppingCarRemoveMapper;
import com.demo.poc.entrypoint.shoppingcart.management.mapper.ShoppingCartAdditionMapper;
import com.demo.poc.entrypoint.shoppingcart.management.service.ShoppingCartRemoveService;
import javax.inject.Inject;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class ShoppingCartRemoveServiceImpl implements ShoppingCartRemoveService {

    private final ShoppingCartDetailFinderRepositoryHelper shoppingCartDetailFinderRepository;
    private final ShoppingCartDetailCommandDao shoppingCartDetailCommandDao;

    @Inject
    public ShoppingCartRemoveServiceImpl(ShoppingCartDetailFinderRepositoryHelper shoppingCartDetailFinderRepository,
                                         ShoppingCartDetailCommandDao shoppingCartDetailCommandDao) {
        this.shoppingCartDetailFinderRepository = shoppingCartDetailFinderRepository;
        this.shoppingCartDetailCommandDao = shoppingCartDetailCommandDao;
    }


    @Override
    public void removeProductToShoppingCart(ShoppingCartRemoveRequestDto shoppingCartRemoveRequestDto) {
        List<ShoppingCartDetailEntity> shoppingCartDetailList = shoppingCartDetailFinderRepository
                .selectRepository()
                .findAsEntityByClientDocument(shoppingCartRemoveRequestDto.getClient().getDocumentNumber());

        Long shoppingCartId = Optional.of(shoppingCartDetailList)
                .filter(details -> !details.isEmpty())
                .map(details -> details.get(0).getShoppingCartId())
                .orElseThrow(() -> new NoSuchElementException("No such any shoppingCartId"));



        Optional<ShoppingCartDetailEntity> shoppingCartDetailEntityOptional = shoppingCartDetailList
                .stream()
                .filter( detail -> detail.getProductId().equals(shoppingCartRemoveRequestDto.getProduct().getId()))
                .findFirst();

        shoppingCartDetailEntityOptional
                .ifPresentOrElse(detail->{
                    int currentQuantity = detail.getQuantity();
                            int newQuantity = currentQuantity - shoppingCartRemoveRequestDto.getProduct().getQuantity();

                            if (newQuantity <= 0) {
                                detail.setQuantity(newQuantity);
                                shoppingCartDetailCommandDao.updateProduct(detail);
                            } else {
                                shoppingCartDetailCommandDao.removeProductToShoppingCart(detail);
                            }
                },
                        ()->{
                    shoppingCartDetailCommandDao.removeProductToShoppingCart(ShoppingCarRemoveMapper.toEntity(shoppingCartRemoveRequestDto,shoppingCartId));
                        }
                );



    }
}

