package com.auth.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.auth.model.JwtRequest;
import com.auth.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		JwtRequest jwtRequest = userRepository.isUserExists(username);
		if(jwtRequest.getUsername() != null) {
			return new User(jwtRequest.getUsername(),
					jwtRequest.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found !");
		}
	}

}
