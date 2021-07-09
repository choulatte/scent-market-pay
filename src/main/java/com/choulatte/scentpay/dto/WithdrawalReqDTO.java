package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.TransactionType;
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

    public TransactionDTO toTransactionDTO(long balance) {
        return TransactionDTO.builder().accountId(this.accountId)
                .type(TransactionType.WITHDRAWAL)
                .amount(this.amount)
                .balance(balance - this.amount)
                .label(this.label)
                .recordedDate(new Date()).build();
    }
}
