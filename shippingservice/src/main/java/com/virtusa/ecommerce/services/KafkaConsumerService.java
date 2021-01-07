package com.virtusa.ecommerce.services;

import java.time.LocalDate;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.model.StockStatusHistory;
import com.virtusa.ecommerce.repository.ShippingRepository;

@Service
public class KafkaConsumerService {

	@Autowired
	private ShippingRepository shippingRepository;
	private final Logger logger
		= LoggerFactory.getLogger(KafkaConsumerService.class);
	
	@KafkaListener(topics = "${inventory.topic.name}", 
			groupId = "${inventory.topic.group.id}")
	public void consume(String message) {
		logger.info(String.format("Message 1 received -> %s", message));
		String[] data = message.split(",");
		StockStatusHistory stockStatusHistory = new StockStatusHistory();
		if(data[1].equals("available")) {
		stockStatusHistory.setHistoryId(new Random().nextInt(1000000));
		stockStatusHistory.setProductId(Long.parseLong(data[0]));
		stockStatusHistory.setQty(Long.parseLong(data[2]));
		stockStatusHistory.setTimeStamp(LocalDate.now());
		shippingRepository.save(stockStatusHistory);
		logger.info(String.format("Stock status stored --> %s,%s", stockStatusHistory.getProductId(), stockStatusHistory.getQty()));
		}
		else {
			logger.info(String.format("Stock status --> %s", data[1]));
		}
	}
}
