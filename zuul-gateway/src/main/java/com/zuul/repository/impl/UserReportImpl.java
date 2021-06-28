package com.zuul.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zuul.helper.UserRowMapper;
import com.zuul.model.JwtRequest;
import com.zuul.repository.UserRepository;

@Repository
public class UserReportImpl implements UserRepository {


	@Autowired
	JdbcTemplate template;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public JwtRequest isUserExists(String name) {
		String query = "SELECT * FROM user WHERE name = ?";
		
		JwtRequest jwtRequest;
		
		try {
			jwtRequest = template.queryForObject(query, new UserRowMapper(), name);
		} catch (Exception e) {
			return new JwtRequest();
		}
		
		return jwtRequest;
	}
	
}
