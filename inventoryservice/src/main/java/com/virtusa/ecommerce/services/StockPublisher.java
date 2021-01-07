package com.virtusa.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import com.virtusa.ecommerce.facade.InventoryStreams;
import com.virtusa.ecommerce.models.Stock;

@Service
public class StockPublisher {

	
	private final InventoryStreams inventoryStreams;
	
	@Autowired
	private StockService stockService;
	
	public StockPublisher(InventoryStreams inventoryStreams) {
		this.inventoryStreams=inventoryStreams;
	}
	
	public boolean sendStockDetails(long productId) {
		// JPA code skeleton notification.
		MessageChannel messageChannel = inventoryStreams.outboundInventory();
		return messageChannel.send(MessageBuilder
				.withPayload(stockService.getAllStocksAboveBufferLevel(productId))
				.setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
				.build());
	}
	
}
