package com.example.jwt.service;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private final SecretKey secretKey = Keys.hmacShaKeyFor(
			"myverysecretkeymyverysecretkey123456".getBytes());

	public String generateToken(String username) {

		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(
						new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(secretKey)
				.compact();

		return token;
	}

	public String extractUsername(String token) {

		return Jwts.parser()
				.verifyWith(secretKey)
				.build()
				.parseSignedClaims(token)
				.getPayload()
				.getSubject();
	}
}
