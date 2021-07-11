package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.dto.*;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    AccountDTO getAccountInfo(long accountId);
    List<AccountDTO> getAccountInfoList(long userId);
    AccountDTO updateAccountInfo(AccountDTO accountDTO);
}
