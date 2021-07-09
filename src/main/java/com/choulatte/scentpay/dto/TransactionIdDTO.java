package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Transaction;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TransactionIdDTO extends PaymentTypeDTO {

    private Long id;

    public TransactionIdDTO(Transaction transaction) {
        this.id = transaction.getId();
    }
}
