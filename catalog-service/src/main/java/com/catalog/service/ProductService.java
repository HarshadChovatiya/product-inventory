package com.catalog.service;

import com.catalog.model.Product;
import com.catalog.model.ProductList;

public interface ProductService {

	/**
	 * 
	 * @return an object of the class ProductList
	 * @throws Exception
	 */
	ProductList getAllProducts() throws Exception;
	
	/**
	 * 
	 * @param productName string containing the product name
	 * @return an object of the class Product
	 * @throws Exception
	 */
	Product getProductByName(String productName) throws Exception;
}
