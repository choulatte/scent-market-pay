package com.choulatte.scentpay.controller;

import com.choulatte.scentpay.exception.InvalidRequestException;
import com.choulatte.scentpay.service.AccountService;
import com.choulatte.scentpay.service.HoldingService;
import com.choulatte.scentpay.service.TransactionService;
import com.choulatte.scentpay.dto.*;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

    private final AccountService accountService;
    private final HoldingService holdingService;
    private final TransactionService transactionService;

    @GetMapping(value = "/")
    @ApiOperation(value = "계좌 정보 조회", notes = "사용자의 계좌 정보를 조회합니다.")
    public ResponseEntity<AccountDTO> getAccountInfo(@RequestHeader(value = "User-Idx") String userIdx) {
        return ResponseEntity.ok(accountService.getAccountInfo(Long.parseLong(userIdx)));
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "신규 계좌 개설", notes = "사용자의 신규 계좌를 개설합니다.")
    public ResponseEntity<AccountDTO> createAccount(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody AccountDTO accountDTO) {
        if (Long.parseLong(userIdx) != accountDTO.getUserId()) {
            throw new InvalidRequestException();
        }

        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping(value = "/holdings")
    @ApiOperation(value = "홀딩 정보 조회", notes = "사용자의 홀딩 기록을 조회합니다.")
    public ResponseEntity<HoldingSummaryDTO> getHoldingList(@RequestHeader(value = "User-Idx") String userIdx) {
        return ResponseEntity.ok(holdingService.getHoldingSummaryInfo(Long.parseLong(userIdx)));
    }

    @GetMapping(value = "/transactions")
    @ApiOperation(value = "거래 기록 조회", notes = "사용자의 거래 기록을 조회합니다.")
    public ResponseEntity<List<TransactionDTO>> getTransactionList(@RequestHeader(value = "User-Idx") String userIdx, @RequestParam(value = "account_id") Long accountId) {
        if (accountService.getAccountInfoList(Long.parseLong(userIdx)).stream().noneMatch(accountDTO -> Long.parseLong(userIdx) == accountId)) {
            throw new InvalidRequestException();
        }

        return ResponseEntity.ok(transactionService.getTransactionList(accountId));
    }

    @PostMapping(value = "/deposits")
    @ApiOperation(value = "입금", notes = "사용자의 계좌에 입금합니다.")
    public ResponseEntity<TransactionDTO> deposit(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody DepositReqDTO depositReqDTO) {
        if (accountService.getAccountInfoList(Long.parseLong(userIdx)).stream().noneMatch(accountDTO -> Long.parseLong(userIdx) == accountDTO.getUserId())) {
            throw new InvalidRequestException();
        }

        return ResponseEntity.ok(transactionService.deposit(depositReqDTO));
    }

    @PostMapping(value = "/withdrawals")
    @ApiOperation(value = "출금", notes = "사용자의 계좌에서 출금합니다.")
    public ResponseEntity<TransactionDTO> withdrawal(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody WithdrawalReqDTO withdrawalReqDTO) {
        if (accountService.getAccountInfoList(Long.parseLong(userIdx)).stream().noneMatch(accountDTO -> Long.parseLong(userIdx) == accountDTO.getUserId())) {
            throw new InvalidRequestException();
        }

        return ResponseEntity.ok(transactionService.withdraw(withdrawalReqDTO, holdingService.getHoldingSummaryInfo(withdrawalReqDTO.getAccountId())));
    }
}
