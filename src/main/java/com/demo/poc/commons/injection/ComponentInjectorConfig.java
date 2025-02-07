package com.demo.poc.commons.injection;

import static com.demo.poc.commons.constants.Constant.*;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.demo.poc.entrypoint.EcommerceRouterTCP;
import com.demo.poc.entrypoint.products.dao.ProductDao;
import com.demo.poc.entrypoint.products.dao.ProductDaoImpl;
import com.demo.poc.entrypoint.products.service.ProductConsultationService;
import com.demo.poc.entrypoint.products.service.ProductManagementService;
import com.demo.poc.entrypoint.products.service.impl.ProductConsultationServiceImpl;
import com.demo.poc.entrypoint.products.service.impl.ProductManagementServiceImpl;
import com.demo.poc.entrypoint.shoppingcart.dao.ClientDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDao;
import com.demo.poc.entrypoint.shoppingcart.dao.ShoppingCartDetailDao;
import com.demo.poc.entrypoint.shoppingcart.dao.impl.ClientDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.dao.impl.ShoppingCartDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.dao.impl.ShoppingCartDetailDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.service.ShoppingCartConsultationService;
import com.demo.poc.entrypoint.shoppingcart.service.ShoppingCartConsultationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import java.io.IOException;
import java.net.ServerSocket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ComponentInjectorConfig extends AbstractModule {

    @Override
    protected void configure() {
        //product
        bind(ProductDao.class).to(ProductDaoImpl.class);
        bind(ProductConsultationService.class).to(ProductConsultationServiceImpl.class);
        bind(ProductManagementService.class).to(ProductManagementServiceImpl.class);

        //shopping cart
        bind(ClientDao.class).to(ClientDaoImpl.class);
        bind(ShoppingCartDao.class).to(ShoppingCartDaoImpl.class);
        bind(ShoppingCartDetailDao.class).to(ShoppingCartDetailDaoImpl.class);
        bind(ShoppingCartConsultationService.class).to(ShoppingCartConsultationServiceImpl.class);

        bind(ObjectMapper.class);
        bind(EcommerceRouterTCP.class);
        bind(ServerSocket.class).toProvider(ServerSocketProvider.class);
        bind(ConnectionServer.class);
    }

    static class ServerSocketProvider implements Provider<ServerSocket> {

        @Override
        public ServerSocket get() {
            int port = Integer.parseInt(PropertiesReader.getProperty("application.port"));
            try {
                log.info(GREEN + BOLD + "Application started on port " + port + RESET);
                return new ServerSocket(port);
            } catch (IOException exception) {
                throw new RuntimeException("Error creating socket: " + exception.getMessage(), exception);
            }
        }
    }
}
