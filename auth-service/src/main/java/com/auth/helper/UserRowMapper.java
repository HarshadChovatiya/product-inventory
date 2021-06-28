package com.auth.helper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.auth.model.JwtRequest;

public class UserRowMapper implements RowMapper<JwtRequest> {

	@Override
	public JwtRequest mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		JwtRequest jwtRequest = new JwtRequest();
	
		jwtRequest.setUsername(rs.getString("name"));
		jwtRequest.setPassword(rs.getString("password"));
		
		return jwtRequest;
	
	}

}
