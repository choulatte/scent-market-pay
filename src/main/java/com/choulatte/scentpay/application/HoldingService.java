package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.dto.HoldingSummaryDTO;

import java.util.Date;
import java.util.List;

public interface HoldingService {

    HoldingSummaryDTO getHoldingSummaryInfo(long accountId);
    HoldingSummaryDTO getHoldingSummaryInfo(long accountId, Date date);
    HoldingDTO getHoldingInfo(long holdingId);
    List<HoldingDTO> getHoldingList(long accountId, Date date);
    HoldingDTO createHolding(HoldingDTO holdingDTO);
    HoldingDTO changeHoldingStatusClosed(long holdingId);
    HoldingDTO extendHolding(long holdingId, Date newExpiredDate);
}
