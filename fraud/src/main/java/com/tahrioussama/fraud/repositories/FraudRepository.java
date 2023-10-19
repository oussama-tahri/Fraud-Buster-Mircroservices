package com.tahrioussama.fraud.repositories;

import com.tahrioussama.fraud.entities.FraudCheckHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudRepository extends JpaRepository<FraudCheckHistory,Integer> {
}
