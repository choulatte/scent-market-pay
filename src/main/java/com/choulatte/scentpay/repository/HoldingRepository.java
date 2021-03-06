package com.choulatte.scentpay.repository;

import com.choulatte.scentpay.domain.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {

    List<Holding> findByAccountIdAndExpiredDateAfter(Long accountId, Date date);
}
