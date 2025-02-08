package com.demo.poc.entrypoint.shoppingcart.finder.repository;

import com.demo.poc.commons.properties.PropertiesReader;
import com.google.inject.Inject;
import java.util.NoSuchElementException;
import java.util.Set;

public class ShoppingCartDetailFinderRepositoryHelper {

  private final Set<ShoppingCartDetailFinderRepository> shoppingCartDetailFinderRepositorySet;

  @Inject
  public ShoppingCartDetailFinderRepositoryHelper(Set<ShoppingCartDetailFinderRepository> shoppingCartDetailFinderRepositorySet) {
    this.shoppingCartDetailFinderRepositorySet = shoppingCartDetailFinderRepositorySet;
  }

  public ShoppingCartDetailFinderRepository selectRepository() {
    Class<?> selectedClass = PropertiesReader.getPropertyClass("shopping-cart.repository.selector-class");

    return shoppingCartDetailFinderRepositorySet.stream()
        .filter(repository -> repository.supports(selectedClass))
        .findFirst()
        .orElseThrow(() -> new NoSuchElementException("No such selector class"));
  }
}
