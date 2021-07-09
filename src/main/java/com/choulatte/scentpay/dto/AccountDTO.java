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
    private boolean isValid;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.userId = account.getUserId();
        this.balance = account.getBalance();
        this.registeredDate = account.getRegisteredDate();
        this.lastModifiedDate = account.getLastModifiedDate();
        this.statusType = account.getStatusType();
        this.isValid = account.getValidity();
    }
}
