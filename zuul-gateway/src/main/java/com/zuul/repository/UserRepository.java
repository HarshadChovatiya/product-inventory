package com.zuul.repository;

import com.zuul.model.JwtRequest;

public interface UserRepository {

	/**
	 * 
	 * @param name a string containing the name of the user
	 * @return an object of the class JwtRequest
	 */
	JwtRequest isUserExists(String name);
	
}
