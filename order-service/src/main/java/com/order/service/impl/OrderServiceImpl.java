package com.order.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.order.model.Cart;
import com.order.model.CartData;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private OrderRepository orderRepo;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addItemToCart(CartData cartData, String username) throws Exception {
		
		if(!orderRepo.isProductExists(cartData.getProductname())) {
			return "Product that you are looking for is not exists";
		}
		
		if(username == null) {
			username = "Dummy";
		}
		Cart cart = new Cart();
		cart.setUsername(username);
		cart.setProductname(cartData.getProductname());
		cart.setQuantity(cartData.getQuantity());
	
		logger.info("call made to order repository for adding item to the cart");
		return orderRepo.addToCart(cart);
	
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String checkOutAndSavePurchaseReport(String username) throws Exception {
		
		if(username == null) {
			username = "Dummy";
		}
		
		logger.info("call made to order repository for cart empty operation");
		List<CartData> allItemsFromCart = orderRepo.doCartEmpty(username);
		logger.info("cart empty performed");
		
		logger.info("call made to order repository for saving purchase report");
		orderRepo.saveReportToDB(allItemsFromCart, username);
		logger.info("report saved to database");
		
		return "Order placed";
	}

}
