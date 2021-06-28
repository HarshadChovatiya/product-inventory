package com.auth.repository;

import com.auth.model.JwtRequest;

public interface UserRepository {

	/**
	 * 
	 * @param name a string containing the name of the user
	 * @param password a string containing the password
	 * @return integer
	 */
	int addUser(String name,String password);
	
	/**
	 * 
	 * @param name a string containing the name of the user
	 * @return an object of the class JwtRequest
	 */
	JwtRequest isUserExists(String name);
}
