package com.loyalty.programs.repository;

import com.loyalty.programs.model.Giftcard;
import com.loyalty.programs.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftCardRepository extends JpaRepository<Giftcard, String> {
    List<Giftcard> findAllByUserId(String userId);
}
