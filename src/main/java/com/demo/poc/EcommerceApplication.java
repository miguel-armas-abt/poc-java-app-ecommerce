package com.demo.poc;

import com.demo.poc.commons.injection.InjectorConfig;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.google.inject.Guice;
import com.google.inject.Injector;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EcommerceApplication {

  public static void main(String[] args) throws IOException {
    Injector injector = Guice.createInjector(new InjectorConfig());
    ConnectionServer server = injector.getInstance(ConnectionServer.class);
    server.start();
  }
}
