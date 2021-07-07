package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Optional<AccountDTO> createAccount(AccountDTO accountDTO);
    Optional<AccountDTO> getAccountInfo(AccountDTO accountDTO);
    Optional<AccountDTO> updateAccountInfo(AccountDTO accountDTO);
    Optional<TransactionDTO> deposit(DepositDTO depositDTO);
    Optional<TransactionDTO> withdraw(WithdrawalDTO withdrawalDTO);
    Optional<HoldingDTO> getHoldingInfo(long accountId);
    List<HoldingDTO> getHoldingList(long accountId);
    List<TransactionDTO> getTransactionList(long accountId);
}
