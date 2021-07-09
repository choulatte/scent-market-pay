package com.choulatte.scentpay.repository;

import com.choulatte.scentpay.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByIdAndValidityIsTrue(Long id);
    List<Account> findByUserIdAndValidityIsTrue(Long userId);
}
