package com.demo.poc.entrypoint.products.dao;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.*;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.products.entity.ProductEntity;
import com.demo.poc.entrypoint.products.mapper.ProductConsultationMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductDaoImpl implements ProductDao {

  private static final String PRODUCT_CONSULTATION_ATTRIBUTES = " id, name, stock, price, category, description, enabled ";

  @Override
  public List<ProductEntity> findAll() {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT" + PRODUCT_CONSULTATION_ATTRIBUTES + "FROM products";
      statement = connection.prepareStatement(sqlStatement);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      List<ProductEntity> productList = new ArrayList<>();
      while (result.next()) {
        ProductEntity productEntity = ProductConsultationMapper.toEntity(result);
        productList.add(productEntity);
      }
      return productList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find all elements: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public List<ProductEntity> findByCategory(String category) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT" + PRODUCT_CONSULTATION_ATTRIBUTES + "FROM products WHERE category = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setString(1, category);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      List<ProductEntity> productList = new ArrayList<>();
      while (result.next()) {
        ProductEntity product = ProductConsultationMapper.toEntity(result);
        productList.add(product);
      }
      return productList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find elements: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public List<ProductEntity> findLessThanPrice(Double unitPrice) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT " + PRODUCT_CONSULTATION_ATTRIBUTES + "FROM products WHERE price < ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setDouble(1, unitPrice);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      List<ProductEntity> productList = new ArrayList<>();
      while (result.next()) {
        ProductEntity productEntity = ProductConsultationMapper.toEntity(result);
        productList.add(productEntity);
      }
      return productList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find products with price less than " + unitPrice + ": " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public List<ProductEntity> findByNameMatch(String name) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT" + PRODUCT_CONSULTATION_ATTRIBUTES + "FROM products WHERE name LIKE ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setString(1, "%" + name + "%");
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      List<ProductEntity> productList = new ArrayList<>();
      while (result.next()) {
        ProductEntity productEntity = ProductConsultationMapper.toEntity(result);
        productList.add(productEntity);
      }
      return productList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find products by name match: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public ProductEntity findById(Long id) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT " + PRODUCT_CONSULTATION_ATTRIBUTES + "FROM products WHERE id = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setLong(1, id);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      ProductEntity product = null;
      if (result.next())
        product = ProductConsultationMapper.toEntity(result);

      return product;

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find element: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

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
