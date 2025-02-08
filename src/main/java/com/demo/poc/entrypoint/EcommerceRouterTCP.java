package com.demo.poc.entrypoint;

import static com.demo.poc.commons.tcp.TCPResourceHelper.closeResource;

import com.demo.poc.commons.utils.RoutingUtils;
import com.demo.poc.entrypoint.products.management.dto.ProductSaveRequestDto;
import com.demo.poc.entrypoint.products.finder.service.ProductFinderService;
import com.demo.poc.entrypoint.products.management.service.ProductManagementService;
import com.demo.poc.entrypoint.shoppingcart.addition.dto.ShoppingCartAdditionRequestDto;
import com.demo.poc.entrypoint.shoppingcart.addition.service.ShoppingCartAdditionService;
import com.demo.poc.entrypoint.shoppingcart.finder.service.ShoppingCartFinderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import java.io.*;
import java.net.Socket;

public class EcommerceRouterTCP extends Thread {

  private final ObjectMapper objectMapper;
  private final ProductFinderService productFinderService;
  private final ProductManagementService productManagementService;
  private final ShoppingCartFinderService shoppingCartFinderService;
  private final ShoppingCartAdditionService shoppingCartAdditionService;

  private Socket socket;

  @Inject
  public EcommerceRouterTCP(ObjectMapper objectMapper,
                            ProductFinderService productFinderService,
                            ProductManagementService productManagementService,
                            ShoppingCartFinderService shoppingCartFinderService,
                            ShoppingCartAdditionService shoppingCartAdditionService) {
    this.objectMapper = objectMapper;
    this.productFinderService = productFinderService;
    this.productManagementService = productManagementService;
    this.shoppingCartFinderService = shoppingCartFinderService;
    this.shoppingCartAdditionService = shoppingCartAdditionService;
  }

  public void setSocket(Socket socket) {
    this.socket = socket;
  }

  public void run() {
    try(
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter outputWriter = new PrintWriter(socket.getOutputStream(), true)
    ) {
      //receive request
      String endpoint = inputReader.readLine();
      boolean success = false;

      if (endpoint.equals("get/products")) {
        String jsonResponse = objectMapper.writeValueAsString(productFinderService.findAll());
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products/\\d+$")) {
        String productId = endpoint.split("/")[2].trim();
        String jsonResponse = objectMapper.writeValueAsString(productFinderService.findById(Long.parseLong(productId)));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?category=\\w*\\d*$")) {
        String category = RoutingUtils.getQueryParam(endpoint, "category");
        String jsonResponse = objectMapper.writeValueAsString(productFinderService.findByCategory(category));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?lessThanPrice=\\w*\\d*$")) {
        Double price = Double.parseDouble(RoutingUtils.getQueryParam(endpoint, "lessThanPrice"));
        String jsonResponse = objectMapper.writeValueAsString(productFinderService.findLessThanPrice(price));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?nameMatches=\\w*\\d*$")) {
        String nameMatches = RoutingUtils.getQueryParam(endpoint, "nameMatches");
        String jsonResponse = objectMapper.writeValueAsString(productFinderService.findByNameMatch(nameMatches));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^delete/products/\\d+$")) {
        String productId = endpoint.split("/")[2].trim();
        productManagementService.deleteById(Long.parseLong(productId));
        success = true;
      }

      if(endpoint.matches("^post/products/[a-zA-Z0-9+/=]+$")) {
        String jsonRequestBody = RoutingUtils.getBase64DecodeBody(endpoint.split("/")[2].trim());
        ProductSaveRequestDto request = objectMapper.readValue(jsonRequestBody, ProductSaveRequestDto.class);
        productManagementService.save(request);
        success = true;
      }

      if(endpoint.matches("^get/shopping-carts\\?documentNumber=\\w*\\d*$")) {
        String documentNumber = RoutingUtils.getQueryParam(endpoint, "documentNumber");
        String jsonResponse = objectMapper.writeValueAsString(shoppingCartFinderService.retrieveDetailByClientDocument(documentNumber));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^post/shopping-carts/add-product/[a-zA-Z0-9+/=]+$")) {
        String jsonRequestBody = RoutingUtils.getBase64DecodeBody(endpoint.split("/")[3].trim());
        ShoppingCartAdditionRequestDto request = objectMapper.readValue(jsonRequestBody, ShoppingCartAdditionRequestDto.class);
        shoppingCartAdditionService.addProductToShoppingCart(request);
        success = true;
      }

      if(!success)
        throw new IllegalArgumentException("The request '" + endpoint + "' was not processed successfully");

    } catch (IOException exception) {
      throw new RuntimeException("TCP error: " + exception.getMessage(), exception);

    } finally {
      closeResource(socket);
    }
  }

}
