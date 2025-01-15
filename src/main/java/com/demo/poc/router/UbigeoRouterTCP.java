package com.demo.poc.router;

import static com.demo.poc.commons.tcp.TCPResourceHelper.closeResource;

import com.demo.poc.dto.SneakerRequestDto;
import com.demo.poc.dto.SneakerResponseDto;
import com.demo.poc.service.SneakerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;
import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.List;

public class UbigeoRouterTCP extends Thread {

  private final ObjectMapper objectMapper;
  private final SneakerService sneakerService;

  private Socket socket;

  @Inject
  public UbigeoRouterTCP(ObjectMapper objectMapper, SneakerService sneakerService) {
    this.objectMapper = objectMapper;
    this.sneakerService = sneakerService;
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

      if (endpoint.equals("get/sneakers") || endpoint.equals("get/sneakers/")) {
        List<SneakerResponseDto> sneakerList = sneakerService.findAll();
        String jsonResponse = objectMapper.writeValueAsString(sneakerList);

        //write response
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^get/sneakers/\\d+$")) {
        String sneakerId = endpoint.split("/")[2].trim();
        SneakerResponseDto sneaker = sneakerService.findById(Long.parseLong(sneakerId));
        String jsonResponse = objectMapper.writeValueAsString(sneaker);

        //write response
        outputWriter.println(jsonResponse);
        success = true;
      }

      if(endpoint.matches("^delete/sneakers/\\d+$")) {
        String sneakerId = endpoint.split("/")[2].trim();
        sneakerService.deleteById(Long.parseLong(sneakerId));

        success = true;
      }

      if (endpoint.matches("^post/sneakers/[a-zA-Z0-9+/=]+$")) {
        String base64Request = endpoint.split("/")[2].trim();
        byte[] decodedBytes = Base64.getDecoder().decode(base64Request);
        String jsonRequest = new String(decodedBytes);
        SneakerRequestDto request = objectMapper.readValue(jsonRequest,SneakerRequestDto.class);
        sneakerService.save(request);

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
