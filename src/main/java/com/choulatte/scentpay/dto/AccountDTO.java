package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Account;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class AccountDTO implements Serializable {

    private Long id;
    private Long userId;
    private Long balance;
    private Date registeredDate;
    private Date lastModifiedDate;
    private Account.StatusType statusType;
    private Boolean validity;

    public Account toEntity() {
        return Account.builder().userId(this.userId)
                .balance(0L)
                .registeredDate(new Date())
                .lastModifiedDate(new Date())
                .statusType(Account.StatusType.NORMAL)
                .validity(true).build();
    }
}
