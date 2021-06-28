package com.order.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.order.helper.CartDataRowMapper;
import com.order.helper.ProductRowMapper;
import com.order.model.Cart;
import com.order.model.CartData;
import com.order.model.Product;
import com.order.model.UserReport;
import com.order.repository.OrderRepository;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

	Logger logger = LoggerFactory.getLogger(OrderRepositoryImpl.class);
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String addToCart(Cart cart) throws Exception {
		String query = "INSERT INTO cart VALUES(?,?,?)";
		try {
			template.update(query, cart.getUsername(), cart.getProductname(), cart.getQuantity());
		} catch(Exception e) {
			logger.error("Error while adding items to cart"
					+ "error from: order-repository"
					+ e.getMessage());
			throw new Exception(e.getMessage());
		}
		logger.info("Items added to cart");
		return "Item added to cart";
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CartData> doCartEmpty(String username) throws Exception {
		String query = "DELETE FROM cart WHERE username = ?";
		List<CartData> allItemsFromCart;
		try {
			allItemsFromCart = getItemPresentOnCart(username);
			template.update(query, username);
			return allItemsFromCart;
		} catch(Exception e) {
			logger.error("Error while empty cart action - repository");
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String saveReportToDB(List<CartData> allItemsFromCart, String username) {
		
		Query query = new Query();
		query.addCriteria(Criteria.where("username").is(username));
		
		UserReport data = mongoTemplate.findOne(query, UserReport.class);
		
		if(data == null) {
			//  user has not purchase anything before
			//  make new entry
			UserReport userReport = new UserReport();
			userReport.setUsername(username);
			userReport.setPurchasedItems(allItemsFromCart);
			mongoTemplate.save(userReport);
		} else {
			//  user has already purchase some item before
			//  modify purchase report
			allItemsFromCart.forEach(item -> data.getPurchasedItems().add(item));
			mongoTemplate.updateFirst(
					query,
					Update.update("purchasedItems", data.getPurchasedItems()),
					UserReport.class);
		};
		return "Report Added";
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CartData> getItemPresentOnCart(String username) throws Exception {
		String query = "SELECT productname, quantity FROM cart WHERE username = ?";
		try {
			List<CartData> allItemFromCart = template.query(query, new CartDataRowMapper(), username);
			logger.info("items from cart has been fetched");
			return allItemFromCart;
		} catch(Exception e) {
			logger.error("Error while fetching items from cart"
					+ "Error from: order-repository"
					+ e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isProductExists(String productName) {
		String query = "SELECT * FROM product WHERE productname = ?";
		try {
			Product product = template.queryForObject(query, new ProductRowMapper(), productName);
			System.out.println(product == null);
		} catch(Exception e) {
			return false;
		}
		return true;
	}

}
