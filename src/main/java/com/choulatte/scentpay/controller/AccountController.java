package com.choulatte.scentpay.controller;

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
        return ResponseEntity.ok(accountService.getAccountInfo(1));
    }

    @PostMapping(value = "/")
    @ApiOperation(value = "신규 계좌 개설", notes = "사용자의 신규 계좌를 개설합니다.")
    public ResponseEntity<AccountDTO> createAccount(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(accountService.createAccount(accountDTO));
    }

    @GetMapping(value = "/holdings")
    @ApiOperation(value = "홀딩 정보 조회", notes = "사용자의 홀딩 기록을 조회합니다.")
    public ResponseEntity<HoldingSummaryDTO> getHoldingList(@RequestHeader(value = "User-Idx") String userIdx) {
        return ResponseEntity.ok(holdingService.getHoldingSummaryInfo(1));
    }

    @GetMapping(value = "/transactions")
    @ApiOperation(value = "거래 기록 조회", notes = "사용자의 거래 기록을 조회합니다.")
    public ResponseEntity<List<TransactionDTO>> getTransactionList(@RequestHeader(value = "User-Idx") String userIdx) {
        return ResponseEntity.ok(null);
    }

    @PostMapping(value = "/deposits")
    @ApiOperation(value = "입금", notes = "사용자의 계좌에 입금합니다.")
    public ResponseEntity<TransactionDTO> deposit(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody DepositReqDTO req) {
        return ResponseEntity.ok(transactionService.deposit(req));
    }

    @PostMapping(value = "/withdrawals")
    @ApiOperation(value = "출금", notes = "사용자의 계좌에서 출금합니다.")
    public ResponseEntity<TransactionDTO> withdrawal(@RequestHeader(value = "User-Idx") String userIdx, @RequestBody WithdrawalReqDTO req) {
        return ResponseEntity.ok(transactionService.withdraw(req, holdingService.getHoldingSummaryInfo(req.getAccountId())));
    }

}
