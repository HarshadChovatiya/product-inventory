package com.catalog.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalog.model.Product;
import com.catalog.model.ProductList;
import com.catalog.repository.ProductRepository;
import com.catalog.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ProductList getAllProducts() throws Exception {
		ProductList productList = new ProductList();
		productList.setProducts(productRepo.listProducts());
		return productList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product getProductByName(String productName) throws Exception {
		return productRepo.getSingleProduct(productName);
	}

}
