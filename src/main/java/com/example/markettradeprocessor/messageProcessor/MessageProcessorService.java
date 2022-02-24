package com.example.markettradeprocessor.messageProcessor;

import com.example.markettradeprocessor.config.amqp.TradeMessageMQConfiguration;
import com.example.markettradeprocessor.entity.TradeMessage;
import com.example.markettradeprocessor.repository.TradeMessageRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProcessorService {

    @Autowired
    private TradeMessageRepository tradeMessageRepository;

    @RabbitListener(
            queues = TradeMessageMQConfiguration.QUEUE,
            ackMode = "AUTO",
            concurrency = "8"
    )
    public void receiveMessage(final TradeMessage message) {
        tradeMessageRepository.save(message);
    }
}
