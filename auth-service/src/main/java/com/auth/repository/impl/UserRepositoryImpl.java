package com.auth.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.auth.helper.UserRowMapper;
import com.auth.model.JwtRequest;
import com.auth.repository.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {
	
	@Autowired
	JdbcTemplate template;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int addUser(String name,String password) {
		String query = "INSERT INTO user VALUES(?,?)";
		return template.update(query,name,passwordEncoder.encode(password));
	}
	
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