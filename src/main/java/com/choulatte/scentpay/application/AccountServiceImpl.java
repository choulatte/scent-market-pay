package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.domain.Transaction;
import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import com.choulatte.scentpay.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;
    private final HoldingRepository holdingRepository;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return accountRepository.save(accountDTO.toEntity()).toDTO();
    }

    @Override
    public Optional<AccountDTO> getAccountInfo(long accountId) {
        return getAccount(accountId).map(Account::toDTO);
    }

    @Override
    public List<AccountDTO> getAccountInfoList(long userId) {
        return getAccountList(userId).stream().map(Account::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccountInfo(AccountDTO accountDTO) {
        return accountRepository.save(getAccount(accountDTO.getId())
                .orElseThrow(RuntimeException::new).updateInfo(accountDTO)).toDTO();
    }

    @Override
    @Transactional
    public TransactionDTO deposit(DepositReqDTO depositReqDTO) {
        Account account = getAccount(depositReqDTO.getAccountId()).orElseThrow(RuntimeException::new);

        if (account.getStatusType() == AccountStatusType.FREEZING) throw new RuntimeException();

        Transaction transaction = transactionRepository.save(depositReqDTO.toTransactionDTO(account.getBalance()).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return new TransactionDTO(transaction);
    }

    @Override
    @Transactional
    public TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO) {
        Account account = getAccount(withdrawalReqDTO.getAccountId()).orElseThrow(RuntimeException::new);

        // TODO: withdraw() is very critical service method. More constraint rules are needed here.
        if (account.getStatusType() != AccountStatusType.NORMAL) throw new RuntimeException();
        if (account.getBalance() < withdrawalReqDTO.getAmount()) throw new RuntimeException();

        Transaction transaction = transactionRepository.save(withdrawalReqDTO.toTransactionDTO(account.getBalance()).toEntity(account));
        accountRepository.save(account.applyTransaction(transaction));

        return new TransactionDTO(transaction);
    }

    @Override
    public Optional<HoldingDTO> getHoldingInfo(long accountId) {
        return Optional.empty();
    }

    @Override
    public List<HoldingDTO> getHoldingList(long accountId) {
        return holdingRepository.findByAccountId(accountId).stream().map(HoldingDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<TransactionDTO> getTransactionList(long accountId) {
        return transactionRepository.findByAccountId(accountId).stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    private Optional<Account> getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId);
    }

    private List<Account> getAccountList(long userId) {
        return accountRepository.findByUserIdAndValidityIsTrue(userId);
    }
}
