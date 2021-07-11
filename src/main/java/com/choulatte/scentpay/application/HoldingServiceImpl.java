package com.choulatte.scentpay.application;

import com.choulatte.scentpay.dto.HoldingDTO;
import com.choulatte.scentpay.repository.AccountRepository;
import com.choulatte.scentpay.repository.HoldingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HoldingServiceImpl implements HoldingService {

    private final AccountRepository accountRepository;
    private final HoldingRepository holdingRepository;

    @Override
    public HoldingDTO getHoldingInfo(long accountId) {
        // TODO: implement
        return null;
    }

    @Override
    public List<HoldingDTO> getHoldingList(long accountId) {
        return holdingRepository.findByAccountId(accountId).stream().map(HoldingDTO::new).collect(Collectors.toList());
    }
}
