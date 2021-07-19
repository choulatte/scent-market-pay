package com.choulatte.scentpay.config;

import com.choulatte.scentpay.grpc.PaymentServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GrpcServerConfig {

    @Value("${grpc.server.port}")
    private int grpcServerPort;

    @Bean
    public Server grpcServer(PaymentServiceImpl paymentService) {
        return ServerBuilder.forPort(grpcServerPort)
                .addService(paymentService).build();
    }
}
