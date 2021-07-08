package com.choulatte.scentpay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class PaymentReqDTO<T extends PaymentTypeDTO> {

    private final T reqDetailsDTO;
}
