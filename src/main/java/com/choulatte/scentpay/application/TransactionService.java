package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.DepositReqDTO;
import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.dto.TransactionDTO;
import com.choulatte.scentpay.dto.WithdrawalReqDTO;

import java.util.List;

public interface TransactionService {

    TransactionDTO deposit(DepositReqDTO depositReqDTO);
    TransactionDTO withdraw(WithdrawalReqDTO withdrawalReqDTO, HoldingDTO holdingDTO);
    List<TransactionDTO> getTransactionList(long accountId);
}
