package com.order.service;

import com.order.model.CartData;

public interface OrderService {

	/**
	 * 
	 * @param cartData an object of the class CartData
	 * @param username a string containing the name of the user
	 * @return String
	 * @throws Exception
	 */
	String addItemToCart(CartData cartData, String username) throws Exception;
	
	/**
	 * 
	 * @param username a string containing the name of the user
	 * @return String
	 * @throws Exception
	 */
	String checkOutAndSavePurchaseReport(String username) throws Exception;
}
