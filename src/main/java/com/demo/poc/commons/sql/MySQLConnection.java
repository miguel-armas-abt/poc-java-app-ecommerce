package com.demo.poc.commons.sql;

import static com.demo.poc.commons.sql.SQLResourceHelper.closeResource;

import com.demo.poc.commons.properties.PropertiesReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Patrón de diseño Singleton, instancia una única conexión a base de datos.
 */
public class MySQLConnection {

  private static Connection connection = null;

  public static Connection getConnection() {
    try {
      if (connection == null) {
        Runtime.getRuntime().addShutdownHook(new ShutdownHook());

        String driver = PropertiesReader.getProperty("database.driver");
        String url = PropertiesReader.getProperty("database.url");
        String password = PropertiesReader.getProperty("database.password");
        String user = PropertiesReader.getProperty("database.user");

        Class.forName(driver);
        connection = DriverManager.getConnection(url, user, password);
      }
      return connection;
    } catch (SQLException | ClassNotFoundException exception) {
      throw new RuntimeException("Connection could not be established: " + exception.getMessage(), exception);
    }
  }

  /**
   * En caso de alguna interrupción inesperada el método run se ejecutará para liberar recursos asociados a la conexión.
   */
  static class ShutdownHook extends Thread {
    public void run() {
      closeResource(MySQLConnection.getConnection());
    }
  }

}
