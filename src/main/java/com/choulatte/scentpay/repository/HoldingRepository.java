package com.choulatte.scentpay.repository;

import com.choulatte.scentpay.domain.Holding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HoldingRepository extends JpaRepository<Holding, Long> {


}
