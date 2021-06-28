package com.catalog.model;

import java.util.List;

public class ProductList {

	private List<Product> products;

	public ProductList() {
		super();
	}

	public ProductList(List<Product> products) {
		super();
		this.products = products;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
}
