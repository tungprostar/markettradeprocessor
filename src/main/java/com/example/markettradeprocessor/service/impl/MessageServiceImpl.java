package com.example.markettradeprocessor.service.impl;

import com.example.markettradeprocessor.model.response.TradeMessageResponse;
import com.example.markettradeprocessor.repository.TradeMessageRepository;
import com.example.markettradeprocessor.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private TradeMessageRepository tradeMessageRepository;

    @Override
    public List<TradeMessageResponse> fetchLatestData(String userId) {
        var listTradeHistory = tradeMessageRepository.findByUserId(userId);
        return listTradeHistory.stream().map(history -> {
           TradeMessageResponse t = TradeMessageResponse.builder().amountBuy(history.getAmountBuy())
                   .amountSell(history.getAmountSell())
                   .timePlaced(history.getTimePlaced())
                   .build();
           return t;
        }).collect(Collectors.toList());
    }
}
