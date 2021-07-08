package com.choulatte.scentpay.repository;

import com.choulatte.scentpay.domain.Account;
import com.choulatte.scentpay.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);
}
