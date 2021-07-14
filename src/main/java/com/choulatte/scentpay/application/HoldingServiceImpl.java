package com.choulatte.scentpay.application;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.domain.Holding;
import com.choulatte.scentpay.domain.HoldingStatusType;
import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.dto.HoldingSummaryDTO;
import com.choulatte.scentpay.exception.AccountNotFoundException;
import com.choulatte.scentpay.exception.HoldingNotFoundException;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoldingServiceImpl implements HoldingService {

    private final AccountRepository accountRepository;
    private final HoldingRepository holdingRepository;

    @Override
    public HoldingSummaryDTO getHoldingSummaryInfo(long accountId) {
        return getHoldingSummaryInfo(accountId, new Date());
    }

    @Override
    public HoldingSummaryDTO getHoldingSummaryInfo(long accountId, Date date) {
        return new HoldingSummaryDTO(getHoldingList(accountId, date));
    }

    @Override
    public HoldingDTO getHoldingInfo(long holdingId) {
        return getHolding(holdingId).toDTO();
    }

    @Override
    public List<HoldingDTO> getHoldingList(long accountId, Date date) {
        return holdingRepository.findByAccountIdAndExpiredDateAfter(accountId, date).stream().map(Holding::toDTO).collect(Collectors.toList());
    }

    @Override
    public HoldingDTO createHolding(HoldingDTO holdingDTO) {
        return holdingRepository.save(holdingDTO.toEntity(getAccount(holdingDTO.getAccountId()))).toDTO();
    }

    @Override
    public HoldingDTO changeHoldingStatusClosed(long holdingId) {
        return holdingRepository.save(getHolding(holdingId).updateStatus(HoldingStatusType.CLOSED)).toDTO();
    }

    @Override
    public HoldingDTO extendHolding(long holdingId, Date newExpiredDate) {
        return holdingRepository.save(getHolding(holdingId).updateExpiredDate(newExpiredDate)).toDTO();
    }

    private Account getAccount(long accountId) {
        return accountRepository.findByIdAndValidityIsTrue(accountId).orElseThrow(AccountNotFoundException::new);
    }

    private Holding getHolding(long holdingId) {
        return holdingRepository.findById(holdingId).orElseThrow(HoldingNotFoundException::new);
    }
}
