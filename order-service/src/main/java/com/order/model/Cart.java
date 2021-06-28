package com.order.model;

public class Cart {

	private String username;
	private String productname;
	private int quantity;
	
	public Cart() {
		super();
	}
	
	public Cart(String username, String productname, int quantity) {
		super();
		this.username = username;
		this.productname = productname;
		this.quantity = quantity;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
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
