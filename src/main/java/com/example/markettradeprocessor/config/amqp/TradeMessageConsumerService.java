package com.example.markettradeprocessor.config.amqp;

import com.example.markettradeprocessor.entity.TradeMessage;
import com.example.markettradeprocessor.repository.TradeMessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeMessageConsumerService {

    @Autowired
    private TradeMessageRepository tradeMessageRepository;

    @RabbitListener(queues = TradeMessageMQConfiguration.QUEUE)
    public void receiveMessage(final TradeMessage message) {
        tradeMessageRepository.save(message);
    }
}
