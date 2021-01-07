package com.virtusa.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.services.KafkaProducerService;

@RestController
public class ShippingController {
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	@GetMapping("/shipping/v1.0/{productId}")
	public void publishPlannedDeliveryDate(@PathVariable("productId") long productId){
		kafkaProducerService.sendMessage(productId);
	}
}
