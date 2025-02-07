package com.demo.poc.entrypoint.shoppingcart.dao.impl;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDao;
import com.demo.poc.entrypoint.shoppingcart.entity.ShoppingCartEntity;
import com.demo.poc.entrypoint.shoppingcart.mapper.ShoppingCartMapper;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class ShoppingCartDaoImpl implements ShoppingCartDao {

  @Override
  public ShoppingCartEntity findShoppingCartByClientId(Long clientId) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT id, client_id FROM shopping_carts WHERE client_id = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setLong(1, clientId);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      ShoppingCartEntity shoppingCart = null;
      if (result.next())
        shoppingCart = ShoppingCartMapper.toEntity(result);

      return shoppingCart;

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find element: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }
}
