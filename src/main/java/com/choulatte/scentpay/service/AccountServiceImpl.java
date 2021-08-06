package com.choulatte.scentpay.service;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.dto.*;
import com.choulatte.scentpay.exception.AccountNotFoundException;
import com.choulatte.scentpay.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO createAccount(@NotNull AccountDTO accountDTO) {
        return accountRepository.save(accountDTO.toEntity()).toDTO();
    }

    @Override
    @Cacheable(value = "account", key = "#accountId", unless = "#result == null")
    public AccountDTO getAccountInfo(long accountId) {
        return getAccount(accountId).toDTO();
    }

    @Override
    public List<AccountDTO> getAccountInfoList(long userId) {
        return getAccountList(userId).stream().map(Account::toDTO).collect(Collectors.toList());
    }

    @Override
    @CacheEvict(value = "account", key = "#accountDTO.id", condition = "#result != null")
    public AccountDTO updateAccountInfo(@NotNull AccountDTO accountDTO) {
        return accountRepository.save(getAccount(accountDTO.getId()).updateInfo(accountDTO)).toDTO();
    }

    private Account getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId).orElseThrow(AccountNotFoundException::new);
    }

    private List<Account> getAccountList(long userId) {
        return accountRepository.findByUserIdAndValidityIsTrue(userId);
    }
}
