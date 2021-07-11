package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.domain.Transaction;
import com.choulatte.scentpay.dto.DepositReqDTO;
import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.dto.TransactionDTO;
import com.choulatte.scentpay.dto.WithdrawalReqDTO;
import com.choulatte.scentpay.exception.AccountBalanceShortageException;
import com.choulatte.scentpay.exception.AccountIllegalStateException;
import com.choulatte.scentpay.exception.AccountNotFoundException;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public TransactionDTO deposit(DepositReqDTO depositReqDTO) {
        Account account = getAccount(depositReqDTO.getAccountId()).orElseThrow(AccountNotFoundException::new);

        if (account.getStatusType() == AccountStatusType.FREEZING) throw new AccountIllegalStateException();

        Transaction transaction = transactionRepository.save(depositReqDTO.toTransactionDTO(account.getBalance()).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return new TransactionDTO(transaction);
    }

    @Override
    @Transactional
    public TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO, @NotNull HoldingDTO holdingDTO) {
        Account account = getAccount(withdrawalReqDTO.getAccountId()).orElseThrow(AccountNotFoundException::new);

        if (account.getStatusType() != AccountStatusType.NORMAL) throw new AccountIllegalStateException();
        if (account.getBalance() < withdrawalReqDTO.getAmount()) throw new AccountBalanceShortageException();

        Transaction transaction = transactionRepository.save(withdrawalReqDTO.toTransactionDTO(account.getBalance()).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return new TransactionDTO(transaction);
    }

    @Override
    public List<TransactionDTO> getTransactionList(long accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    private Optional<Account> getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId);
    }
}
