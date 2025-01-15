package com.demo.poc.dao;

import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResource;
import static com.demo.poc.commons.sql.SQLResourceHelper.rollback;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entity.SneakerEntity;
import com.demo.poc.mapper.SneakerMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SneakerDaoImpl implements SneakerDao {

  private static final String SAVE = "INSERT INTO sneakers (brand, price, qualification, provider, gender, size, type) VALUES (?, ?, ?, ?, ?, ?, ?);";
  private static final String DELETE_BY_ID = "DELETE FROM sneakers WHERE id = ?";
  private static final String FIND_ALL = "SELECT id, brand, price, qualification, provider, gender, size, type FROM sneakers;";
  private static final String FIND_BY_ID = "SELECT id, brand, price, qualification, provider, gender, size, type FROM sneakers WHERE id = ?";

  @Override
  public void save(SneakerEntity sneaker) {
    Connection connection = null;
    PreparedStatement statement = null;
    try {
      connection = MySQLConnection.getConnection();
      connection.setAutoCommit(false);

      statement = connection.prepareStatement(SAVE);
      statement.setString(1, sneaker.getBrand());
      statement.setDouble(2, sneaker.getPrice());
      statement.setInt(3, sneaker.getQualification());
      statement.setString(4, sneaker.getProvider());
      statement.setString(5, sneaker.getGender());
      statement.setDouble(6, sneaker.getSize());
      statement.setString(7, sneaker.getType());

      int insertedRows = statement.executeUpdate();

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
      statement = connection.prepareStatement(DELETE_BY_ID);
      statement.setLong(1, id);

      int deletedRows = statement.executeUpdate();

      if (deletedRows != 1)
        throw new RuntimeException("Error to delete element with id " + id);

      connection.commit();

    } catch (SQLException exception) {
      rollback(connection);
    } finally {
      closeResource(statement);
    }
  }

  @Override
  public List<SneakerEntity> findAll() {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      statement = connection.prepareStatement(FIND_ALL);
      result = statement.executeQuery();

      List<SneakerEntity> employeeList = new ArrayList<>();
      while (result.next()) {
        SneakerEntity employeeEntity = SneakerMapper.toEntity(result);
        employeeList.add(employeeEntity);
      }
      return employeeList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find all elements: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @Override
  public SneakerEntity findById(Long id) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      statement = connection.prepareStatement(FIND_BY_ID);
      statement.setLong(1, id);
      result = statement.executeQuery();

      SneakerEntity employee = null;
      if (result.next())
        employee = SneakerMapper.toEntity(result);

      if(employee != null)
        return employee;

      throw new IllegalArgumentException("No such element with id:" + id);

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find element by id: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }
}
