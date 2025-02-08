package com.demo.poc.commons.injection;

import static com.demo.poc.commons.constants.Constant.*;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.demo.poc.entrypoint.EcommerceRouterTCP;
import com.demo.poc.entrypoint.products.consultation.dao.ProductConsultationDao;
import com.demo.poc.entrypoint.products.consultation.dao.ProductConsultationDaoImpl;
import com.demo.poc.entrypoint.products.consultation.service.ProductConsultationService;
import com.demo.poc.entrypoint.products.management.dao.ProductManagementDao;
import com.demo.poc.entrypoint.products.management.dao.ProductManagementDaoImpl;
import com.demo.poc.entrypoint.products.management.service.ProductManagementService;
import com.demo.poc.entrypoint.products.consultation.service.ProductConsultationServiceImpl;
import com.demo.poc.entrypoint.products.management.service.ProductManagementServiceImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.ClientDao;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.ShoppingCartDao;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.ShoppingCartDetailDao;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.ShoppingCartDetailJoinDao;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.impl.ClientDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.impl.ShoppingCartDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.impl.ShoppingCartDetailDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.dao.impl.ShoppingCartDetailJoinDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.repository.impl.ShoppingCartCompositeRepositoryImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.repository.ShoppingCartRepository;
import com.demo.poc.entrypoint.shoppingcart.consultation.repository.impl.ShoppingCartJoinRepositoryImpl;
import com.demo.poc.entrypoint.shoppingcart.consultation.service.ShoppingCartConsultationService;
import com.demo.poc.entrypoint.shoppingcart.consultation.service.ShoppingCartConsultationServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import java.io.IOException;
import java.net.ServerSocket;

import com.google.inject.multibindings.Multibinder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InjectorConfig extends AbstractModule {

    @Override
    protected void configure() {
        //product consultation
        bind(ProductConsultationDao.class).to(ProductConsultationDaoImpl.class);
        bind(ProductConsultationService.class).to(ProductConsultationServiceImpl.class);

        //product consultation
        bind(ProductManagementDao.class).to(ProductManagementDaoImpl.class);
        bind(ProductManagementService.class).to(ProductManagementServiceImpl.class);

        //shopping cart consultation
        bind(ClientDao.class).to(ClientDaoImpl.class);
        bind(ShoppingCartDao.class).to(ShoppingCartDaoImpl.class);
        bind(ShoppingCartDetailDao.class).to(ShoppingCartDetailDaoImpl.class);
        bind(ShoppingCartDetailJoinDao.class).to(ShoppingCartDetailJoinDaoImpl.class);

        Multibinder<ShoppingCartRepository> binderSet = Multibinder.newSetBinder(binder(), ShoppingCartRepository.class);
        binderSet.addBinding().to(ShoppingCartCompositeRepositoryImpl.class);
        binderSet.addBinding().to(ShoppingCartJoinRepositoryImpl.class);

        bind(ShoppingCartRepository.class).to(ShoppingCartCompositeRepositoryImpl.class);
        bind(ShoppingCartConsultationService.class).to(ShoppingCartConsultationServiceImpl.class);

        // commons
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
