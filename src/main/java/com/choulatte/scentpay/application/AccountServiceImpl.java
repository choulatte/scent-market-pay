package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.domain.Transaction;
import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.exception.AccountBalanceShortageException;
import com.choulatte.scentpay.exception.AccountIllegalStateException;
import com.choulatte.scentpay.exception.AccountNotFoundException;
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
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {
        return accountRepository.save(accountDTO.toEntity()).toDTO();
    }

    @Override
    public AccountDTO getAccountInfo(long accountId) {
        return getAccount(accountId).map(Account::toDTO).orElseThrow(AccountNotFoundException::new);
    }

    @Override
    public List<AccountDTO> getAccountInfoList(long userId) {
        return getAccountList(userId).stream().map(Account::toDTO).collect(Collectors.toList());
    }

    @Override
    public AccountDTO updateAccountInfo(AccountDTO accountDTO) {
        return accountRepository.save(getAccount(accountDTO.getId())
                .orElseThrow(AccountNotFoundException::new).updateInfo(accountDTO)).toDTO();
    }

    private Optional<Account> getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId);
    }

    private List<Account> getAccountList(long userId) {
        return accountRepository.findByUserIdAndValidityIsTrue(userId);
    }
}
