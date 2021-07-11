package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.Transaction;
import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.exception.AccountNotFoundException;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionDTO deposit(DepositReqDTO depositReqDTO) {
        Account account = getAccount(depositReqDTO.getAccountId());
        Transaction transaction = transactionRepository.save(depositReqDTO.toTransactionDTO(account).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return transaction.toDTO();
    }

    @Override
    @Transactional
    public TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO, @NotNull HoldingSummaryDTO holdingSummaryDTO) {
        Account account = getAccount(withdrawalReqDTO.getAccountId());
        Transaction transaction = transactionRepository.save(withdrawalReqDTO.toTransactionDTO(account, holdingSummaryDTO).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return transaction.toDTO();
    }

    @Override
    public List<TransactionDTO> getTransactionList(long accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(Transaction::toDTO).collect(Collectors.toList());
    }

    private Account getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId).orElseThrow(AccountNotFoundException::new);
    }
}
