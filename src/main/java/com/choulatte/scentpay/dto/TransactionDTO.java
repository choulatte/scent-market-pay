package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Transaction;
import com.choulatte.scentpay.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionDTO extends TransactionIdDTO {

    private Long accountId;
    private TransactionType type;
    private Long amount;
    private Long balance;
    private String label;
    private Date recordedDate;

    TransactionDTO(Transaction transaction) {
        super(transaction);

        this.accountId = transaction.getAccount().getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.balance = transaction.getBalance();
        this.label = transaction.getLabel();
        this.recordedDate = transaction.getRecordedDate();
    }
}
