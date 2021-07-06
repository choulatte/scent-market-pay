package com.choulatte.scentpay.dto;

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
    private Boolean isValid;
}
