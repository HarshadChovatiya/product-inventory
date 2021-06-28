package com.order.repository;

import java.util.List;

import com.order.model.Cart;
import com.order.model.CartData;

public interface OrderRepository {

	/**
	 * 
	 * @param cart an object of the class Cart
	 * @return String
	 * @throws Exception 
	 */
	String addToCart(Cart cart) throws Exception;
	
	/**
	 * 
	 * @param username a string contains the name of the user
	 * @return a list containing the objects of the class CartData
	 * @throws Exception 
	 */
	List<CartData> doCartEmpty(String username) throws Exception;
	
	/**
	 * 
	 * @param username a string contains the name of the user
	 * @return a list containing the objects of the class CartData
	 * @throws Exception
	 */
	List<CartData> getItemPresentOnCart(String username) throws Exception;
	
	/**
	 * 
	 * @param allItemsFromCart a list of objects of the class CartData
	 * @param username a string containing the name of the user
	 * @return String
	 */
	String saveReportToDB(List<CartData> allItemsFromCart, String username);
	
	/**
	 * 
	 * @param productName string
	 * @return boolean
	 */
	boolean isProductExists(String productName);
}
