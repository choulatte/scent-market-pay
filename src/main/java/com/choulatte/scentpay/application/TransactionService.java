package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.*;

import java.util.List;

public interface TransactionService {

    TransactionDTO deposit(DepositReqDTO depositReqDTO);
    TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO, HoldingSummaryDTO holdingSummaryDTO);
    List<TransactionDTO> getTransactionList(long accountId);
}
