package com.demo.poc.commons.injection;

import static com.demo.poc.commons.constants.Constant.*;

import com.demo.poc.commons.properties.PropertiesReader;
import com.demo.poc.dao.SneakerDao;
import com.demo.poc.dao.SneakerDaoImpl;
import com.demo.poc.router.ConnectionServer;
import com.demo.poc.router.SneakerRouterTCP;
import com.demo.poc.service.SneakerService;
import com.demo.poc.service.SneakerServiceImpl;
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
        bind(SneakerDao.class).to(SneakerDaoImpl.class);
        bind(SneakerService.class).to(SneakerServiceImpl.class);
        bind(ObjectMapper.class);
        bind(SneakerRouterTCP.class);
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
