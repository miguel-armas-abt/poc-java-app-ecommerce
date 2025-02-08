package com.demo.poc.commons.injection;

import static com.demo.poc.commons.constants.Constant.*;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.commons.tcp.ConnectionServer;
import com.demo.poc.entrypoint.EcommerceRouterTCP;
import com.demo.poc.entrypoint.products.finder.dao.ProductQueryDao;
import com.demo.poc.entrypoint.products.finder.dao.ProductQueryDaoImpl;
import com.demo.poc.entrypoint.products.finder.service.ProductFinderService;
import com.demo.poc.entrypoint.products.management.dao.ProductCommandDao;
import com.demo.poc.entrypoint.products.management.dao.ProductCommandDaoImpl;
import com.demo.poc.entrypoint.products.management.service.ProductManagementService;
import com.demo.poc.entrypoint.products.finder.service.ProductFinderServiceImpl;
import com.demo.poc.entrypoint.products.management.service.ProductManagementServiceImpl;
import com.demo.poc.entrypoint.shoppingcart.addition.dao.ShoppingCartDetailCommandDao;
import com.demo.poc.entrypoint.shoppingcart.addition.dao.ShoppingCartDetailCommandDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.addition.repository.ShoppingCartAdditionRepository;
import com.demo.poc.entrypoint.shoppingcart.addition.repository.ShoppingCartAdditionRepositoryImpl;
import com.demo.poc.entrypoint.shoppingcart.addition.service.ShoppingCartAdditionService;
import com.demo.poc.entrypoint.shoppingcart.addition.service.ShoppingCartAdditionServiceImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ClientQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartDetailQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.ShoppingCartDetailJoinQueryDao;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.impl.ClientQueryDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.impl.ShoppingCartQueryDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.impl.ShoppingCartDetailQueryDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.dao.impl.ShoppingCartDetailJoinQueryDaoImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepositoryHelper;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.impl.ShoppingCartDetailCompositeRepositoryImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.ShoppingCartDetailFinderRepository;
import com.demo.poc.entrypoint.shoppingcart.finder.repository.impl.ShoppingCartDetailJoinQueryRepositoryImpl;
import com.demo.poc.entrypoint.shoppingcart.finder.service.ShoppingCartFinderService;
import com.demo.poc.entrypoint.shoppingcart.finder.service.ShoppingCartFinderServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.multibindings.Multibinder;
import java.io.IOException;
import java.net.ServerSocket;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InjectorConfig extends AbstractModule {

    @Override
    protected void configure() {
        //product finder
        bind(ProductQueryDao.class).to(ProductQueryDaoImpl.class);
        bind(ProductFinderService.class).to(ProductFinderServiceImpl.class);

        //product management
        bind(ProductCommandDao.class).to(ProductCommandDaoImpl.class);
        bind(ProductManagementService.class).to(ProductManagementServiceImpl.class);

        //shopping cart finder
        bind(ClientQueryDao.class).to(ClientQueryDaoImpl.class);
        bind(ShoppingCartQueryDao.class).to(ShoppingCartQueryDaoImpl.class);
        bind(ShoppingCartDetailQueryDao.class).to(ShoppingCartDetailQueryDaoImpl.class);
        bind(ShoppingCartDetailJoinQueryDao.class).to(ShoppingCartDetailJoinQueryDaoImpl.class);

        Multibinder<ShoppingCartDetailFinderRepository> binderSet = Multibinder.newSetBinder(binder(), ShoppingCartDetailFinderRepository.class);
        binderSet.addBinding().to(ShoppingCartDetailCompositeRepositoryImpl.class);
        binderSet.addBinding().to(ShoppingCartDetailJoinQueryRepositoryImpl.class);

        bind(ShoppingCartDetailFinderRepositoryHelper.class);
        bind(ShoppingCartDetailFinderRepository.class).to(ShoppingCartDetailCompositeRepositoryImpl.class);
        bind(ShoppingCartFinderService.class).to(ShoppingCartFinderServiceImpl.class);

        //shopping cart addition
        bind(ShoppingCartDetailCommandDao.class).to(ShoppingCartDetailCommandDaoImpl.class);
        bind(ShoppingCartAdditionRepository.class).to(ShoppingCartAdditionRepositoryImpl.class);
        bind(ShoppingCartAdditionService.class).to(ShoppingCartAdditionServiceImpl.class);

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
