package com.choulatte.scentpay.grpc;

import com.choulatte.pay.grpc.PaymentServiceGrpc;
import com.choulatte.pay.grpc.PaymentServiceOuterClass;
import io.grpc.stub.StreamObserver;

public class PaymentServiceImpl extends PaymentServiceGrpc.PaymentServiceImplBase {

    @Override
    public void doPayment(PaymentServiceOuterClass.TransactionRequest request, StreamObserver<PaymentServiceOuterClass.TransactionResponse> responseObserver) {
        super.doPayment(request, responseObserver);
    }

    @Override
    public void doHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.HoldingResponse> responseObserver) {
        super.doHolding(request, responseObserver);
    }

    @Override
    public void extendHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.HoldingResponse> responseObserver) {
        super.extendHolding(request, responseObserver);
    }

    @Override
    public void clearHolding(PaymentServiceOuterClass.HoldingRequest request, StreamObserver<PaymentServiceOuterClass.Response> responseObserver) {
        super.clearHolding(request, responseObserver);
    }
}
