package com.demo.poc.entrypoint.shoppingcart.finder.dao.impl;

import static com.demo.poc.commons.constants.Constant.PURPLE;
import static com.demo.poc.commons.constants.Constant.RESET;
import static com.demo.poc.commons.sql.SQLResourceHelper.closeResources;

import com.demo.poc.commons.sql.MySQLConnection;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartDetailJoinQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.entity.ShoppingCartDetailJoin;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ShoppingCartDetailJoinQueryDaoImpl implements ShoppingCartDetailJoinQueryDao {

  private static final String JOIN_SQL_QUERY =
      """
          
          SELECT p.id, p.name, p.price, psc.quantity
          FROM products p
          JOIN products_shopping_carts psc ON p.id = psc.product_id
          JOIN shopping_carts sc ON psc.shopping_cart_id = sc.id
          JOIN clients c ON sc.client_id = c.id
          WHERE c.document_number = ?""";

  @Override
  public List<ShoppingCartDetailJoin> findByClientDocument(String documentNumber) {
    PreparedStatement statement = null;
    ResultSet result = null;
    try {
      Connection connection = MySQLConnection.getConnection();
      statement = connection.prepareStatement(JOIN_SQL_QUERY);
      statement.setString(1, documentNumber);
      result = statement.executeQuery();
      log.info(PURPLE + JOIN_SQL_QUERY + RESET);

      List<ShoppingCartDetailJoin> detailList = new ArrayList<>();
      while (result.next()) {
        ShoppingCartDetailJoin detail = ShoppingCartDetailJoinDaoMapper.toJoin(result);
        detailList.add(detail);
      }
      return detailList;
    } catch (SQLException exception) {
      throw new RuntimeException("Error to find elements: " + exception.getMessage(), exception);
    } finally {
      closeResources(statement, result);
    }
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  private static class ShoppingCartDetailJoinDaoMapper {

    public static ShoppingCartDetailJoin toJoin(ResultSet result) throws SQLException {
      return ShoppingCartDetailJoin.builder()
          .productId(result.getLong("id"))
          .productName(result.getString("name"))
          .unitPrice(result.getDouble("price"))
          .quantity(result.getInt("quantity"))
          .build();
    }
  }
}
