package com.demo.poc.entrypoint.shoppingcart.dao.impl;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.dao.ClientDao;
import com.demo.poc.entrypoint.shoppingcart.entity.ClientEntity;
import com.demo.poc.entrypoint.shoppingcart.mapper.ClientMapper;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Slf4j
public class ClientDaoImpl implements ClientDao {

  @Override
  public ClientEntity findByDocumentNumber(String documentNumber) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      String sqlStatement = "SELECT id, name, document_number, document_type FROM clients WHERE document_number = ?";
      statement = connection.prepareStatement(sqlStatement);
      statement.setString(1, documentNumber);
      result = statement.executeQuery();
      log.info(PURPLE + sqlStatement + RESET);

      ClientEntity client = null;
      if (result.next())
        client = ClientMapper.toEntity(result);

      return client;

    } catch (SQLException exception) {
      throw new RuntimeException("Error to find element: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }
}
