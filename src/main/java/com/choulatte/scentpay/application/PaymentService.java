package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentRespDTO<TransactionDTO>> processPayment(PaymentReqDTO<TransactionDTO> paymentReqDTO);
    Optional<PaymentRespDTO<HoldingDTO>> processHolding(PaymentReqDTO<HoldingDTO> paymentReqDTO);
    Optional<PaymentRespDTO<TransactionDTO>> cancelPayment(PaymentReqDTO<TransactionIdDTO> paymentReqDTO);
    Optional<PaymentRespDTO<HoldingDTO>> clearHolding(PaymentReqDTO<HoldingIdDTO> paymentReqDTO);
}
