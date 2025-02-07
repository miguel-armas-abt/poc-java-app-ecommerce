package com.demo.poc.entrypoint;

import static com.demo.poc.commons.tcp.TCPResourceHelper.closeResource;

import com.demo.poc.commons.utils.RoutingUtils;
import com.demo.poc.entrypoint.products.dto.ProductToSaveRequestDto;
import com.demo.poc.entrypoint.products.service.ProductConsultationService;
import com.demo.poc.entrypoint.products.service.ProductManagementService;
import com.demo.poc.entrypoint.shoppingcart.service.ShoppingCartConsultationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.Map;

public class EcommerceRouterTCP extends Thread {

  private final ObjectMapper objectMapper;
  private final ProductConsultationService productConsultationService;
  private final ProductManagementService productManagementService;
  private final ShoppingCartConsultationService shoppingCartConsultationService;

  private Socket socket;

  @Inject
  public EcommerceRouterTCP(ObjectMapper objectMapper,
                            ProductConsultationService productConsultationService,
                            ProductManagementService productManagementService,
                            ShoppingCartConsultationService shoppingCartConsultationService) {
    this.objectMapper = objectMapper;
    this.productConsultationService = productConsultationService;
    this.productManagementService = productManagementService;
    this.shoppingCartConsultationService = shoppingCartConsultationService;
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
        String jsonResponse = objectMapper.writeValueAsString(productConsultationService.findAll());
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products/\\d+$")) {
        String productId = endpoint.split("/")[2].trim();
        String jsonResponse = objectMapper.writeValueAsString(productConsultationService.findById(Long.parseLong(productId)));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?category=\\w*\\d*$")) {
        String category = RoutingUtils.getQueryParam(endpoint, "category");
        String jsonResponse = objectMapper.writeValueAsString(productConsultationService.findByCategory(category));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?lessThanPrice=\\w*\\d*$")) {
        Double price = Double.parseDouble(RoutingUtils.getQueryParam(endpoint, "lessThanPrice"));
        String jsonResponse = objectMapper.writeValueAsString(productConsultationService.findLessThanPrice(price));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/products\\?nameMatches=\\w*\\d*$")) {
        String nameMatches = RoutingUtils.getQueryParam(endpoint, "nameMatches");
        String jsonResponse = objectMapper.writeValueAsString(productConsultationService.findByNameMatch(nameMatches));
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^delete/products/\\d+$")) {
        String productId = endpoint.split("/")[2].trim();
        productManagementService.deleteById(Long.parseLong(productId));
        success = true;
      }

      if(endpoint.matches("^post/products/[a-zA-Z0-9+/=]+$")) {
        String base64Request = endpoint.split("/")[2].trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Request);
        String jsonRequest = new String(decodedBytes);
        ProductToSaveRequestDto request = objectMapper.readValue(jsonRequest, ProductToSaveRequestDto.class);
        productManagementService.save(request);
        success = true;
      }

      if(endpoint.matches("^get/shopping-carts\\?documentNumber=\\w*\\d*$")) {
        String documentNumber = RoutingUtils.getQueryParam(endpoint, "documentNumber");
        String jsonResponse = objectMapper.writeValueAsString(shoppingCartConsultationService.retrieveShoppingCartDetailByClientDocumentNumber(documentNumber));
        outputWriter.println(jsonResponse);
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
