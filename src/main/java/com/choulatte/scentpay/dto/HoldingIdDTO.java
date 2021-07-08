package com.choulatte.scentpay.dto;

import com.choulatte.scentpay.domain.Holding;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HoldingIdDTO extends PaymentTypeDTO {

    private Long id;

    HoldingIdDTO(Holding holding) {
        this.id = holding.getId();
    }
}
