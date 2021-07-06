package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TransactionDTO {

    private Long id;
    private Long accountId;
    private TransactionType type;
    private Long amount;
    private Long balance;
    private String label;
    private Date recordedDate;
}
