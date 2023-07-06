package com.loyalty.programs.repository;

import com.loyalty.programs.model.Award;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AwardRepository extends JpaRepository<Award, String> {
    List<Award> findAllByUserId(String userId);
}
