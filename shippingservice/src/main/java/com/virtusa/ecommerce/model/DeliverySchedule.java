package com.virtusa.ecommerce.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeliverySchedule {
	private long productId;
	private LocalDate requestDate;
	private LocalDate plannedDeliveryDate;
	private long availableQty;

}
