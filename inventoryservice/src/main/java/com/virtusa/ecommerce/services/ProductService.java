package com.virtusa.ecommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.virtusa.ecommerce.models.Product;
import com.virtusa.ecommerce.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public Product addProduct(Product product) {
		return this.productRepo.save(product);
	}
	
	public List<Product> finalAllProducts(){
		return productRepo.findAll();
	}
	
	public Product getProductById(long productId) {
		return productRepo.findById(productId).orElse(null);
	}
}
