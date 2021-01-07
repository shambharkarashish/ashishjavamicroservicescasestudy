package com.virtusa.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virtusa.ecommerce.models.Stock;
import com.virtusa.ecommerce.services.StockPublisher;
import com.virtusa.ecommerce.services.StockService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/stocks")
public class StockController {

	@Autowired
	private StockService service;
	@Autowired
	private StockPublisher stockPublisher;
	
	@PostMapping({"/v1.0/{productId}/{locationId}", "/v1.1/{productId}/{locationId}"})
	
	@CrossOrigin("*")
	public ResponseEntity<?> addStock(@RequestBody Stock stock,
			@PathVariable("productId") long productId,
			@PathVariable("locationId") long locationId){
		Stock stockObj = service.addStock(stock, productId, locationId);
		if(stockObj != null) {
			log.info("Stock added successfully");
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(stock);
		}
		else {
			log.info("Stock Not added");
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Stock Not added");
		}
	}
	
	@GetMapping({"/v1.0", "/v1.1"})
	
	@CrossOrigin("*")
	public List<Stock> findAllStocks(){
		return service.findAllStocks();
	}
	
	@GetMapping({"/v1.0/publish/{productId}", "/v1.1/publish/{productId}"})
	 public ResponseEntity<?> publishStock(@PathVariable("productId") long productId)
	 {
		
			if(this.stockPublisher.sendStockDetails(productId))
				return ResponseEntity.ok("Stock Published");
			else
		    	return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Stock Not Available");

	 }
	
}
