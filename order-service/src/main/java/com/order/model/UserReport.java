package com.order.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_report")
public class UserReport {

	String username;
	List<CartData> purchasedItems;
	
	public UserReport() {
		super();
	}
	
	public UserReport(String username, List<CartData> purchasedItems) {
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
	
	public List<CartData> getPurchasedItems() {
		return purchasedItems;
	}
	
	public void setPurchasedItems(List<CartData> purchasedItems) {
		this.purchasedItems = purchasedItems;
	}

}
