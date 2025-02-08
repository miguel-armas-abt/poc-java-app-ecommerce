package com.demo.poc.entrypoint.shoppingcart.finder.dao.impl;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class ShoppingCartQueryDaoImpl implements ShoppingCartQueryDao {

  @Override
  public ShoppingCartEntity findByClientId(Long clientId) {
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
        shoppingCart = ShoppingCartDaoMapper.toEntity(result);

      return shoppingCart;

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find element: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ShoppingCartDaoMapper {

    public static ShoppingCartEntity toEntity(ResultSet result) throws SQLException {
      return ShoppingCartEntity.builder()
          .id(result.getLong("id"))
          .clientId(result.getLong("client_id"))
          .build();
    }
  }
}
