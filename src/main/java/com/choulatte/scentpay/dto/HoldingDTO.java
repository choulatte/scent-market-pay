package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.Holding;
import com.choulatte.scentpay.domain.HoldingStatusType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HoldingDTO {

    private Long id;
    private Long accountId;
    private Long amount;
    private Long balance;
    private Date expiredDate;
    private Date recordedDate;
    private Date lastModifiedDate;
    private HoldingStatusType statusType;

    public Holding toEntity(Account account) {
        return Holding.builder().account(account)
                .amount(this.amount)
                .balance(account.getBalance())
                .expiredDate(this.expiredDate)
                .recordedDate(new Date())
                .lastModifiedDate(new Date())
                .statusType(HoldingStatusType.HOLDED).build();
    }
}
