package com.example.markettradeprocessor.controller;

import com.example.markettradeprocessor.config.amqp.TradeMessageProducerService;
import com.example.markettradeprocessor.entity.TradeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeMessageController {

    @Autowired
    private TradeMessageProducerService tradeMessageProducerService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void processMessage(@RequestBody TradeMessage tradeMessage) {
        tradeMessageProducerService.dispatch(tradeMessage);
    }
}
