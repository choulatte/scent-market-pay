package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AccountService accountService;
    private final HoldingService holdingService;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public Optional<PaymentRespDTO<TransactionDTO>> processPayment(PaymentReqDTO<TransactionDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<PaymentRespDTO<HoldingDTO>> processHolding(PaymentReqDTO<HoldingDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<PaymentRespDTO<TransactionDTO>> cancelPayment(PaymentReqDTO<TransactionIdDTO> paymentReqDTO) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<PaymentRespDTO<HoldingDTO>> clearHolding(PaymentReqDTO<HoldingIdDTO> paymentReqDTO) {
        return Optional.empty();
    }
}
