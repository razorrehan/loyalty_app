package com.loyalty.programs.repository;

import com.loyalty.programs.model.Award;
import com.loyalty.programs.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    List<Transaction> findAllByUserId(String userId);
}
