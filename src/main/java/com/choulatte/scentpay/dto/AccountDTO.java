package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.AccountStatusType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AccountDTO {

    private Long id;
    private Long userId;
    private Long balance;
    private Date registeredDate;
    private Date lastModifiedDate;
    private AccountStatusType statusType;
    private Boolean validity;

    public Account toEntity() {
        return Account.builder().userId(this.userId)
                .balance(0L)
                .registeredDate(new Date())
                .lastModifiedDate(new Date())
                .statusType(AccountStatusType.NORMAL)
                .validity(this.validity).build();
    }
}
