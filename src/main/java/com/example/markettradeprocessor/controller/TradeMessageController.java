package com.example.markettradeprocessor.controller;

import com.example.markettradeprocessor.config.amqp.TradeMessageProducerService;
import com.example.markettradeprocessor.entity.TradeMessage;
import com.example.markettradeprocessor.model.request.TradeMessageRequest;
import com.example.markettradeprocessor.model.response.TradeMessageResponse;
import com.example.markettradeprocessor.service.MessageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeMessageController {

    @Autowired
    private TradeMessageProducerService tradeMessageProducerService;

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/message", method = RequestMethod.POST)
    public void processMessage(@RequestBody TradeMessageRequest tradeMessageRequest) {
        TradeMessage tradeMessage = new TradeMessage();
        BeanUtils.copyProperties(tradeMessageRequest, tradeMessage);
        tradeMessageProducerService.dispatch(tradeMessage);
    }

    @RequestMapping(value = "/fetchTradeMessageHistory/{userId}", method = RequestMethod.GET)
    public List<TradeMessageResponse> fetchTradeMessageHistory(@PathVariable String userId) {
        return messageService.fetchLatestData(userId);
    }
}
