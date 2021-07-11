package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.HoldingDTO;

import java.util.List;
import java.util.Optional;

public interface HoldingService {
    HoldingDTO getHoldingInfo(long accountId);
    List<HoldingDTO> getHoldingList(long accountId);
}
