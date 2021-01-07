package com.virtusa.ecommerce.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.virtusa.ecommerce.model.StockStatusHistory;

public interface ShippingRepository extends MongoRepository<StockStatusHistory, Long> {

}
