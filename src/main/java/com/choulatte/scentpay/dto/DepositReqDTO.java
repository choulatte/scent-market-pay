package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.domain.TransactionType;
import com.choulatte.scentpay.exception.AccountIllegalStateException;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class DepositReqDTO {

    private Long accountId;
    private Long amount;
    private String label;

    public TransactionDTO toTransactionDTO(Account account) {
        if (account.getStatusType() == AccountStatusType.FREEZING) throw new AccountIllegalStateException();

        return TransactionDTO.builder().accountId(this.accountId)
                .type(TransactionType.DEPOSIT)
                .amount(this.amount)
                .balance(account.getBalance() + this.amount)
                .label(this.label)
                .recordedDate(new Date()).build();
    }
}
