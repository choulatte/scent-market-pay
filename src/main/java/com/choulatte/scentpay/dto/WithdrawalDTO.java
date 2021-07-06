package com.choulatte.scentpay.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class WithdrawalDTO {

    private Long accountId;
    private Long amount;
    private Long balance;
    private String label;
}
