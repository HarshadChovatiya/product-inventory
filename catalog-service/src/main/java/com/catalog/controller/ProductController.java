package com.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.catalog.client.OrderServiceClient;
import com.catalog.model.CartData;
import com.catalog.model.JsonResponse;
import com.catalog.model.Product;
import com.catalog.model.ProductList;
import com.catalog.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/product")
public class ProductController {

	Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderServiceClient orderServiceClient;
	
	/**
	 * 
	 * @return an object of the ResponseEntity containing the 
	 * ProductList and HTTP status code
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/all")
	public ResponseEntity<ProductList> getAllProducts() throws Exception{
		try {
			return new ResponseEntity<ProductList>(
					productService.getAllProducts(),
					HttpStatus.OK);
		} catch(Exception e) {
			logger.error("Error while getting all products from database");
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	/**
	 * 
	 * @param productName string contains the name of the product
	 * @return an object of the ResponseEntity containing the 
	 * Product and HTTP status code
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.GET, value = "/{product-name}")
	public ResponseEntity<Product> getProductByName(
			@PathVariable("product-name") String productName) throws Exception{
		try {
			return new ResponseEntity<Product>(
					productService.getProductByName(productName),
					HttpStatus.OK);
		} catch(Exception e) {
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @param cartData an object of the class CartData
	 * @param username a string containing the name of the user
	 * @return an object of the ResponseEntity containing the 
	 * JsonResponse and HTTP status code
	 */
	@HystrixCommand(fallbackMethod = "fallbackGetResponseFromAddToCartOfOrderService")
	@RequestMapping(method = RequestMethod.POST, value = "/addtocart")
	public ResponseEntity<JsonResponse> getResponseFromAddToCartOfOrderService(
			@RequestBody CartData cartData,
			@RequestHeader("UserName") String username) {
		return orderServiceClient.callAddToCart(cartData, username);
	}
	
	/**
	 * 
	 * @param cartData an object of the class CartData
	 * @param username a string containing the name of the user
	 * @return an object of the ResponseEntity containing the 
	 * JsonResponse and HTTP status code
	 */
	public ResponseEntity<JsonResponse> fallbackGetResponseFromAddToCartOfOrderService(
			CartData cartData, String username) {
		return new ResponseEntity<JsonResponse>(
				new JsonResponse("sorry " + username + "....add to cart failed, try again after some time"),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
