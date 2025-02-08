package com.demo.poc.entrypoint.shoppingcart.addition.dao;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResource;
import static com.demo.poc.commons.sql.SQLResourceHelper.rollback;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailEntity;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class ShoppingCartDetailCommandDaoImpl implements ShoppingCartDetailCommandDao {

  @Override
  public void addNewProductToShoppingCart(ShoppingCartDetailEntity shoppingCartDetail) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);

      String sqlStatement = "INSERT INTO products_shopping_carts (product_id, shopping_cart_id, quantity) VALUES (?, ?, ?)";
      statement = connection.prepareStatement(sqlStatement);
      statement.setLong(1, shoppingCartDetail.getProductId());
      statement.setLong(2, shoppingCartDetail.getShoppingCartId());
      statement.setInt(3, shoppingCartDetail.getQuantity());

      int insertedRows = statement.executeUpdate();
      log.info(PURPLE + sqlStatement + RESET);

      if (insertedRows != 1)
        throw new RuntimeException("Error to save element");

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }

  @Override
  public void updateProduct(ShoppingCartDetailEntity shoppingCartDetail) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);

      String sqlStatement = "UPDATE products_shopping_carts SET quantity = ? WHERE product_id = ? AND shopping_cart_id = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setInt(1, shoppingCartDetail.getQuantity());
      statement.setLong(2, shoppingCartDetail.getProductId());
      statement.setLong(3, shoppingCartDetail.getShoppingCartId());

      int updatedRows = statement.executeUpdate();
      log.info(PURPLE + sqlStatement + RESET);

      if (updatedRows != 1)
        throw new RuntimeException("Error to save element");

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }
}
