package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDTO createAccount(AccountDTO accountDTO);
    Optional<AccountDTO> getAccountInfo(long accountId);
    List<AccountDTO> getAccountInfoList(long userId);
    AccountDTO updateAccountInfo(AccountDTO accountDTO);
    TransactionDTO deposit(DepositReqDTO depositReqDTO);
    TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO);
    Optional<HoldingDTO> getHoldingInfo(long accountId);
    List<HoldingDTO> getHoldingList(long accountId);
    List<TransactionDTO> getTransactionList(long accountId);
}
