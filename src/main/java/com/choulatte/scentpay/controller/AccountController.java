package com.choulatte.scentpay.controller;

import com.choulatte.scentpay.application.AccountService;
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

    @GetMapping(value = "/")
    @ApiOperation(value = "계좌 정보 조회", notes = "사용자의 계좌 정보를 조회합니다.")
    public ResponseEntity<AccountDTO> getAccountInfo() {
        return accountService.getAccountInfo(0).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/holdings")
    @ApiOperation(value = "홀딩 기록 조회", notes = "사용자의 홀딩 기록을 조회합니다.")
    public ResponseEntity<List<HoldingDTO>> getHoldingList() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/transactions")
    @ApiOperation(value = "거래 기록 조회", notes = "사용자의 거래 기록을 조회합니다.")
    public ResponseEntity<List<TransactionDTO>> getTransactionList() {
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/deposits")
    @ApiOperation(value = "입금", notes = "사용자의 계좌에 입금합니다.")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody DepositReqDTO req) {
        return ResponseEntity.ok(accountService.deposit(req));
    }

    @PutMapping(value = "/withdrawals")
    @ApiOperation(value = "출금", notes = "사용자의 계좌에서 출금합니다.")
    public ResponseEntity<TransactionDTO> withdrawal(@RequestBody WithdrawalReqDTO req) {
        return ResponseEntity.ok(accountService.withdraw(req));
    }

}
