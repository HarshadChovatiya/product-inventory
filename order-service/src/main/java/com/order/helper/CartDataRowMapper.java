package com.order.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.order.model.CartData;

public class CartDataRowMapper implements RowMapper<CartData> {

	@Override
	public CartData mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartData cartData = new CartData();
		cartData.setProductname(rs.getString("productname"));
		cartData.setQuantity(rs.getInt("quantity"));
		return cartData;
	}

}
