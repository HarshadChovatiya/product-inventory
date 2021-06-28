package com.order.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.order.model.CartData;
import com.order.model.JsonResponse;
import com.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private OrderService orderService;
	
	/**
	 * 
	 * @param cartData object of the class CartData
	 * @param username a string containing the name of the logged in user
	 * @return an object of the ResponseEntity
	 * @throws Exception will throw any exception that has occurred in the 
	 * method call from this method
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/addtocart")
	public ResponseEntity<JsonResponse> addItemToCart(
			@RequestBody CartData cartData,
			@RequestHeader("UserName") String username) throws Exception {
		return new ResponseEntity<JsonResponse>(
				new JsonResponse(orderService.addItemToCart(cartData, username)),
				HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * @param username a string containing the name of the logged in user
	 * @return an object of the ResponseEntity
	 * @throws Exception Exception will throw any exception that has occurred in the 
	 * method call from this method
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/checkout")
	public ResponseEntity<JsonResponse> doCheckOut(@RequestHeader("UserName") String username) throws Exception {
		return new ResponseEntity<JsonResponse>(
				new JsonResponse(orderService.checkOutAndSavePurchaseReport(username)),
				HttpStatus.OK);
	}
	
}
