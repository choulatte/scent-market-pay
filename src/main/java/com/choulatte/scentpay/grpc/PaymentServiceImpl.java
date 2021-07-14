package com.choulatte.scentpay.grpc;

import com.choulatte.pay.grpc.PaymentServiceGrpc;
import com.choulatte.pay.grpc.PaymentServiceOuterClass;
import com.choulatte.scentpay.application.HoldingService;
import com.choulatte.scentpay.application.TransactionService;
import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.exception.*;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final HoldingService holdingService;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public void doPayment(PaymentServiceOuterClass.TransactionRequest request, StreamObserver<PaymentServiceOuterClass.TransactionResponse> responseObserver) {
        super.doPayment(request, responseObserver);
    }

    @Override
    @Transactional
    public void doHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.HoldingResponse> responseObserver) {
        try {
            HoldingDTO holdingDTO = holdingService.createHolding(HoldingDTO.builder().accountId(request.getHolding().getAccountId())
                .amount(request.getHolding().getAmount())
                .expiredDate(new Date(request.getHolding().getExpiredDate())).build());

            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.OK).build())
                    .setHolding(PaymentServiceOuterClass.Holding.newBuilder()
                            .setId(holdingDTO.getId())
                            .setAccountId(holdingDTO.getAccountId())
                            .setAmount(holdingDTO.getAmount())
                            .setBalance(holdingDTO.getBalance())
                            .setExpiredDate(holdingDTO.getExpiredDate().getTime())
                            .setRecordedDate(holdingDTO.getRecordedDate().getTime())
                            .setLastModifiedDate(holdingDTO.getLastModifiedDate().getTime())
                            .setStatus(PaymentServiceOuterClass.Holding.Status.valueOf(holdingDTO.getStatusType().name()))).build());
        } catch (AccountNotFoundException e) {
            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.NOT_FOUND).build()).build());
        } catch (AccountBalanceShortageException e) {
            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.CONFLICT).build()).build());
        }

        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void extendHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.HoldingResponse> responseObserver) {
        try {
            HoldingDTO holdingDTO = holdingService.extendHolding(request.getHolding().getId(), new Date(request.getHolding().getExpiredDate()));

            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.OK).build())
                    .setHolding(PaymentServiceOuterClass.Holding.newBuilder()
                            .setId(holdingDTO.getId())
                            .setAccountId(holdingDTO.getAccountId())
                            .setAmount(holdingDTO.getAmount())
                            .setBalance(holdingDTO.getBalance())
                            .setExpiredDate(holdingDTO.getExpiredDate().getTime())
                            .setRecordedDate(holdingDTO.getRecordedDate().getTime())
                            .setLastModifiedDate(holdingDTO.getLastModifiedDate().getTime())
                            .setStatus(PaymentServiceOuterClass.Holding.Status.valueOf(holdingDTO.getStatusType().name()))).build());
        } catch (HoldingNotFoundException e) {
            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.NOT_FOUND).build()).build());
        } catch (InvalidRequestException e) {
            responseObserver.onNext(PaymentServiceOuterClass.HoldingResponse.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.CONFLICT).build()).build());
        }

        responseObserver.onCompleted();
    }

    @Override
    @Transactional
    public void clearHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.Response> responseObserver) {
        try {
            holdingService.changeHoldingStatusClosed(request.getHolding().getId());

            responseObserver.onNext(PaymentServiceOuterClass.Response.newBuilder()
                    .setResult(PaymentServiceOuterClass.Response.Result.OK).build());
        } catch (HoldingNotFoundException e) {
            responseObserver.onNext(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.NOT_FOUND).build());
        } catch (HoldingIllegalStateException e) {
            responseObserver.onNext(PaymentServiceOuterClass.Response.newBuilder()
                            .setResult(PaymentServiceOuterClass.Response.Result.CONFLICT).build());
        }

        responseObserver.onCompleted();
    }
}
