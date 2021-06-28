package com.report.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_report")
public class UserReport {

	private String username;
	private List<PurchaseDetail> purchasedItems;
	
	public UserReport() {
		super();
	}
	
	public UserReport(String username, List<PurchaseDetail> purchasedItems) {
		super();
		this.username = username;
		this.purchasedItems = purchasedItems;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public List<PurchaseDetail> getPurchasedItems() {
		return purchasedItems;
	}
	
	public void setPurchasedItems(List<PurchaseDetail> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}
	
}
