package com.example.markettradeprocessor.service;

import com.example.markettradeprocessor.model.response.TradeMessageResponse;

import java.util.List;

public interface MessageService {
    List<TradeMessageResponse> fetchLatestData(String userId);
}
