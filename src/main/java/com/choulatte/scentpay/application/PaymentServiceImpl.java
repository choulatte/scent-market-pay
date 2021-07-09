package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final HoldingRepository holdingRepository;

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

    private Optional<Account> getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId);
    }
}
