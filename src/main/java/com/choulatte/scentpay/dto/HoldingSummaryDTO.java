package com.choulatte.scentpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class HoldingSummaryDTO {

    private Long amount;
    private Date summarizedDate;
    private List<HoldingDTO> holdingDTOList;

    public HoldingSummaryDTO(List<HoldingDTO> holdingDTOList) {
        this.amount = 0L;

        holdingDTOList.forEach(holdingDTO -> amount += holdingDTO.getAmount());

        this.summarizedDate = new Date();
        this.holdingDTOList = holdingDTOList;
    }
}
