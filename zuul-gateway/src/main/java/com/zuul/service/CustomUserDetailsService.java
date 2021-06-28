package com.zuul.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.zuul.model.JwtRequest;
import com.zuul.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		JwtRequest jwtRequest = userRepository.isUserExists(userName);
		if(jwtRequest.getUsername() != null) {
			return new User(jwtRequest.getUsername(),
					jwtRequest.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found !");
		}
		
	}

}