package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    @Override
    public Optional<PaymentRespDTO<TransactionDTO>> processPayment(PaymentReqDTO<TransactionDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentRespDTO<HoldingDTO>> processHolding(PaymentReqDTO<HoldingDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentRespDTO<TransactionDTO>> cancelPayment(PaymentReqDTO<TransactionIdDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentRespDTO<HoldingDTO>> clearHolding(PaymentReqDTO<HoldingIdDTO> paymentReqDTO) {
        return Optional.empty();
    }
}
