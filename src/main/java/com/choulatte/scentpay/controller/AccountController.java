package com.choulatte.scentpay.controller;

import com.choulatte.scentpay.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/accounts")
public class AccountController {

    @GetMapping(value = "/")
    public ResponseEntity<AccountDTO> getAccountInfo() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/holdings")
    public ResponseEntity<List<HoldingDTO>> getHoldingList() {
        return ResponseEntity.ok(null);
    }

    @GetMapping(value = "/transactions")
    public ResponseEntity<List<TransactionDTO>> getTransactionList() {
        return ResponseEntity.ok(null);
    }

    @PutMapping(value = "/deposits")
    public ResponseEntity<TransactionDTO> deposit(@RequestBody DepositDTO req) {
        return ResponseEntity.accepted().build();
    }

    @PutMapping(value = "/withdrawals")
    public ResponseEntity<TransactionDTO> withdrawal(@RequestBody WithdrawalDTO req) {
        return ResponseEntity.accepted().build();
    }

}