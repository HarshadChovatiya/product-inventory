package com.report.model;

public class PurchaseDetail {

	private String productname;
	private int quantity;
	
	public PurchaseDetail() {
		super();
	}
	
	public PurchaseDetail(String productname, int quantity) {
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
