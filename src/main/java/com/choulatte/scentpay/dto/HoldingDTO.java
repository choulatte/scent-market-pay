package com.choulatte.scentpay.dto;

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
}
