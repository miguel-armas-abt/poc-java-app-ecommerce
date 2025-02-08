package com.demo.poc.entrypoint.products.management.dao;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResource;
import static com.demo.poc.commons.sql.SQLResourceHelper.rollback;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.products.finder.entity.ProductEntity;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class ProductCommandDaoImpl implements ProductCommandDao {

  @Override
  public void save(ProductEntity product) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);

      String sqlStatement = "INSERT INTO products (name, stock, price, category, description, enabled) VALUES (?, ?, ?, ?, ?, ?)";
      statement = connection.prepareStatement(sqlStatement);
      statement.setString(1, product.getName());
      statement.setInt(2, product.getStock());
      statement.setDouble(3, product.getUnitPrice());
      statement.setString(4, product.getCategory());
      statement.setString(5, product.getDescription());
      statement.setBoolean(6, Boolean.TRUE);

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
  public void deleteById(Long id) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);
      String sqlStatement = "DELETE FROM products WHERE id = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setLong(1, id);

      int deletedRows = statement.executeUpdate();
      log.info(PURPLE + sqlStatement + RESET);

      if (deletedRows != 1)
        throw new RuntimeException("Error to delete element with id " + id);

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }
}
