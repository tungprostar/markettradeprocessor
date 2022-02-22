package com.example.markettradeprocessor.config.amqp;

import com.example.markettradeprocessor.entity.TradeMessage;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class TradeMessageProducerService {

    @Autowired
    @Qualifier("tradeMessageRabbitTemplate")
    private AmqpTemplate tradeMessageRabbitTemplate;

    public void dispatch(final TradeMessage message) {
        tradeMessageRabbitTemplate.convertAndSend(TradeMessageMQConfiguration.EXCHANGE,
                TradeMessageMQConfiguration.ROUTING_KEY, message);
    }
}
