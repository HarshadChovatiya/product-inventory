package com.catalog.repository;

import java.util.List;

import com.catalog.model.Product;

public interface ProductRepository {

	/**
	 * 
	 * @return a list of the objects of the class Product
	 * @throws Exception
	 */
	List<Product> listProducts() throws Exception;
	
	/**
	 * 
	 * @param productName a string containing the product name
	 * @return an object of the class Product
	 * @throws Exception
	 */
	Product getSingleProduct(String productName) throws Exception;
	
}
