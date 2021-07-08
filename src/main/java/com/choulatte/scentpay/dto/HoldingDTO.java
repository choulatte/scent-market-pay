package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Holding;
import com.choulatte.scentpay.domain.HoldingStatusType;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HoldingDTO extends HoldingIdDTO {

    private Long accountId;
    private Long amount;
    private Long balance;
    private Date recordedDate;
    private Date lastModifiedDate;
    private HoldingStatusType statusType;

    HoldingDTO(Holding holding) {
        super(holding);

        this.accountId = holding.getAccount().getId();
        this.amount = holding.getAmount();
        this.balance = holding.getBalance();
        this.recordedDate = holding.getRecordedDate();
        this.lastModifiedDate = holding.getLastModifiedDate();
        this.statusType = holding.getStatusType();
    }
}
