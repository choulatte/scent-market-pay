package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final HoldingRepository holdingRepository;

    @Override
    public Optional<AccountDTO> createAccount(AccountDTO accountDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<AccountDTO> getAccountInfo(AccountDTO accountDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<AccountDTO> updateAccountInfo(AccountDTO accountDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TransactionDTO> deposit(DepositDTO depositDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<TransactionDTO> withdraw(WithdrawalDTO withdrawalDTO) {
        return Optional.empty();
    }

    @Override
    public Optional<HoldingDTO> getHoldingInfo(long accountId) {
        return Optional.empty();
    }

    @Override
    public List<HoldingDTO> getHoldingList(long accountId) {
        return null;
    }

    @Override
    public List<TransactionDTO> getTransactionList(long accountId) {
        return null;
    }
}
