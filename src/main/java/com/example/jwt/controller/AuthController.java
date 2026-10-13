package com.example.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.jwt.service.JwtService;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/login")
	public String login(
			@RequestParam String username,
			@RequestParam String password) {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						username,
						password));

		return jwtService.generateToken(username);
	}
}
