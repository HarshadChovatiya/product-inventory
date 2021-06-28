package com.catalog.repository.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.catalog.helper.ProductRowMapper;
import com.catalog.model.Product;
import com.catalog.repository.ProductRepository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

	Logger logger = LoggerFactory.getLogger(ProductRepositoryImpl.class);
	
	@Autowired
	JdbcTemplate template;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Cacheable(cacheNames = "products")
	public List<Product> listProducts() throws Exception {
		String query = "SELECT * FROM product";
		try {
			return template.query(query, new ProductRowMapper());
		} catch(Exception e) {
			logger.error("Error while fetching data from table product - listProduct - ProductRepository");
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product getSingleProduct(String productName) throws Exception {
		String query = "SELECT * FROM product WHERE productname = ?";
		try {
			return template.queryForObject(query, new ProductRowMapper(), productName);
		} catch(EmptyResultDataAccessException e) {
			logger.error("No product with given product name exists");
			logger.error(e.getMessage());
			throw new EmptyResultDataAccessException("product with given name does not exists", -1);
		} catch(Exception e) {
			logger.error("Error while fetching single product by name - ProductRepository");
			logger.error(e.getMessage());
			throw new Exception(e.getMessage());
		}
	}

}
