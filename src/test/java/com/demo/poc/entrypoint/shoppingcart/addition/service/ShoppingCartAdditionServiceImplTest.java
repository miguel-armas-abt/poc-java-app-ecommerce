package com.demo.poc.entrypoint.shoppingcart.addition.service;

import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepositoryHelper;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ShoppingCartAdditionServiceImplTest {

    public class MockConfig {
        public static ShoppingCartDetailFinderRepositoryHelper buildRepositoryHelper() {
            ShoppingCartDetailFinderRepositoryHelper repositoryHelper = mock(ShoppingCartDetailFinderRepositoryHelper.class);
            //when(repositoryHelper.selectRepository()).thenReturn()
            return null;
        }
    }
}
