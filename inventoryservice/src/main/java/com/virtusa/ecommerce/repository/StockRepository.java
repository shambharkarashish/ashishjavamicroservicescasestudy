package com.virtusa.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.virtusa.ecommerce.models.Stock;

public interface StockRepository extends JpaRepository<Stock, Long>{

	@Query("Select stock from Stock stock where stock.product.productId=:productId")
	public List<Stock> getAllStockByProductId(@Param("productId") long productId);
}
