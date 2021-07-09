package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
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
        return new AccountDTO(accountRepository.save(Account.newInstance(accountDTO)));
    }

    @Override
    public Optional<AccountDTO> getAccountInfo(long accountId) {
        return getAccount(accountId).map(AccountDTO::new);
    }

    @Override
    public List<AccountDTO> getAccountInfoList(long userId) {
        return getAccountList(userId).stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccountInfo(AccountDTO accountDTO) {
        return new AccountDTO(accountRepository.save(getAccount(accountDTO.getId())
                .orElseThrow(RuntimeException::new).updateInfo(accountDTO)));
    }

    @Override
    @Transactional
    public TransactionDTO deposit(DepositDTO depositDTO) {
        Account account = getAccount(depositDTO.getAccountId()).orElseThrow(RuntimeException::new);
        // TODO: implement method to converting DepositDTO to Transaction
        Transaction transaction = transactionRepository.save(Transaction.builder().account(account)
                .amount(depositDTO.getAmount())
                .balance(account.getBalance() + depositDTO.getAccountId())
                .build());

        accountRepository.save(account.applyTransaction(transaction));
        return new TransactionDTO(transactionRepository.save(transaction));
    }

    @Override
    @Transactional
    public TransactionDTO withdraw(WithdrawalDTO withdrawalDTO) {
        return null;
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
