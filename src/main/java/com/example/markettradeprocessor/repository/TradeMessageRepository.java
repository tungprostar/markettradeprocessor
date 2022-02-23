package com.example.markettradeprocessor.repository;

import com.example.markettradeprocessor.entity.TradeMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeMessageRepository extends JpaRepository<TradeMessage, Long> {
    List<TradeMessage> findByUserId(String userId);
}
