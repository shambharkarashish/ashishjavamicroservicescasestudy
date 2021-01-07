package com.virtusa.ecommerce.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.models.Location;
import com.virtusa.ecommerce.models.Product;
import com.virtusa.ecommerce.models.Stock;
import com.virtusa.ecommerce.repository.ProductRepository;
import com.virtusa.ecommerce.repository.StockRepository;

@Service
public class StockService {

	@Autowired
	private StockRepository stockRepoObj;
	
	@Autowired
	private LocationService locationServiceObj;
	
	@Autowired
	private ProductService productServiceObj;
	
	public Stock addStock(Stock stock, long productId, long locationId) {
		Product product = productServiceObj.getProductById(productId);
		Location location = locationServiceObj.getByLocationId(locationId);
		
		if(product != null && location != null) {
			stock.setProduct(product);
			stock.setLocation(location);
			stockRepoObj.save(stock);
		}
		
		return stock;
	}
	
	public List<Stock> findAllStocks(){
		return stockRepoObj.findAll();
	}
	
	public String getAllStocksAboveBufferLevel(long productId)
    {
    	int sum=0;
		for(Stock stock:stockRepoObj.getAllStockByProductId(productId))
		{
			sum+=stock.getQty();
		}
		
		String message=productId+",not Available,"+sum;
		int buffLevel = productServiceObj.getProductById(productId).getBufferLevel();
		if(sum > buffLevel) {
			sum = sum - buffLevel;
			message=productId+",available,"+sum;
		}
		//stockRepository.getAllStockByProductId(productId).stream().
		/*
		 * return stockRepository.findAll().stream().filter(stock->stock.getQty()>
		 * productService.getProductById(stock.getProduct().getProductId())
		 * .getBufferLevel()).collect(Collectors.toList());
		 */
		return message;
    }
	
	/*
	 * public List<Stock> getAllStocksAboveBufferLevel(){ return
	 * stockRepoObj.findAll().stream() .filter(stock -> stock.getQty() >
	 * productServiceObj.getProductById(stock.getProduct().getProductId())
	 * .getBufferLevel()).collect(Collectors.toList()); }
	 */
}
