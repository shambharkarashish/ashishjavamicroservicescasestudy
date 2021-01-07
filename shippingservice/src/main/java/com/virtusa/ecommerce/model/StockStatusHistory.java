package com.virtusa.ecommerce.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Document(collection = "StockStatusHistories")
@Data
public class StockStatusHistory {
	@Id
	private long historyId;
	private long productId;
	private long qty;
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate timeStamp;
	
}
