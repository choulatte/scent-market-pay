package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import com.choulatte.scentpay.domain.TransactionType;
import com.choulatte.scentpay.exception.AccountBalanceShortageException;
import com.choulatte.scentpay.exception.AccountIllegalStateException;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WithdrawalReqDTO {

    private Long accountId;
    private Long amount;
    private String label;

    public TransactionDTO toTransactionDTO(Account account, HoldingSummaryDTO holdingSummaryDTO) {
        if (account.getStatusType() != AccountStatusType.NORMAL) throw new AccountIllegalStateException();
        if (account.getBalance() - holdingSummaryDTO.getAmount() < this.amount) throw new AccountBalanceShortageException();

        return TransactionDTO.builder().accountId(this.accountId)
                .type(TransactionType.WITHDRAWAL)
                .amount(this.amount)
                .balance(account.getBalance() - this.amount)
                .label(this.label)
                .recordedDate(new Date()).build();
    }
}
