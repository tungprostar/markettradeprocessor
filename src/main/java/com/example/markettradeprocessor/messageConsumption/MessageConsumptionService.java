package com.example.markettradeprocessor.messageConsumption;

import com.example.markettradeprocessor.config.amqp.TradeMessageMQConfiguration;
import com.example.markettradeprocessor.entity.TradeMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumptionService {

    @Autowired
    @Qualifier("tradeMessageRabbitTemplate")
    private AmqpTemplate tradeMessageRabbitTemplate;

    public void dispatch(final TradeMessage message) {
        tradeMessageRabbitTemplate.convertAndSend(TradeMessageMQConfiguration.EXCHANGE,
                TradeMessageMQConfiguration.ROUTING_KEY, message);
    }
}
