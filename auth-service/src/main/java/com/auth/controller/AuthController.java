package com.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth.helper.JwtUtil;
import com.auth.model.JwtRequest;
import com.auth.model.JwtResponse;
import com.auth.repository.UserRepository;
import com.auth.service.CustomUserDetailsService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserRepository userRepo;
	
	/**
	 * 
	 * @param jwtRequest an object of the class JwtRequest passed as a request body
	 * @return an object of the class ResponseEntity
	 * @throws Exception
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/token")
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch(UsernameNotFoundException e) {
			throw new Exception("bad credentials");
		}
		UserDetails userDetails;
		try {
			userDetails = this.customUserDetailsService.loadUserByUsername(
					jwtRequest.getUsername());
		} catch(Exception e) {
			throw new Exception("user not found !");
		}
		String token = this.jwtUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	/**
	 * 
	 * @param jwtRequest an object of the class JwtRequest passed as a request body
	 * @return an object of the class ResponseEntity containing the 
	 * String and the HTTP status code
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public ResponseEntity<String> register(@RequestBody JwtRequest jwtRequest) {
		if(userRepo.addUser(jwtRequest.getUsername(), jwtRequest.getPassword()) >= 1) {
			return new ResponseEntity<String>("User registered", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Invalid request body", HttpStatus.BAD_REQUEST);
		}
	}
	
}
