package com.catalog.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.catalog.model.Product;

public class ProductRowMapper implements RowMapper<Product>{

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
		Product product = new Product();
		product.setProductId(rs.getInt("productid"));
		product.setProductName(rs.getString("productname"));
		product.setPrice(rs.getInt("price"));
		product.setCategory(rs.getString("category"));
		
		return product;
	}

}
