package com.example.markettradeprocessor.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TradeMessageResponse {
    private double amountSell;
    private double amountBuy;
    private String timePlaced;
}
