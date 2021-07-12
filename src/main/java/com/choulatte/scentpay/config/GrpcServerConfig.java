package com.choulatte.scentpay.config;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerServiceDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(List<ServerServiceDefinition> grpcServiceList) {
        return ServerBuilder.forPort(grpcServerPort)
                .addServices(grpcServiceList).build();
    }
}
