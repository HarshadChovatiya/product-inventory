package com.catalog.model;

public class CartData {

	private String productname;
	private int quantity;
	
	public CartData() {
		super();
	}

	public CartData(String productname, int quantity) {
		super();
		this.productname = productname;
		this.quantity = quantity;
	}
	
	public String getProductname() {
		return productname;
	}
	
	public void setProductname(String productname) {
		this.productname = productname;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}