package com.example.markettradeprocessor.model.request;

import lombok.Data;

@Data
public class TradeMessageRequest {
    private String userId;
    private String currencyFrom;
    private String currencyTo;
    private double amountSell;
    private double amountBuy;
    private double rate;
    private String timePlaced;
    private String originatingCountry;
}
