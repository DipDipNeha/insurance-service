package com.pscs.insurance.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTGenerator {

	public String generateToken(Authentication authentication) {
		String name = authentication.getName();
		Date currentDate=new Date();
		Date expiryDate =new Date(currentDate.getTime()+SecurityConstant.JWT_EXPIRATION);
		Map<String, Object> claims = new HashMap<>();
//        claims.put("role",role); // Add the role to the claims

		return  Jwts.builder().setSubject(name)
//		.setClaims(claims)
		.setIssuedAt(new Date())
		.setExpiration(expiryDate)
		.signWith(SignatureAlgorithm.HS512,SecurityConstant.secretekey)
		.compact();
	}
	
	public String getUsernameFromJWT(String token) {
		
		Claims claims=Jwts.parser().setSigningKey(SecurityConstant.secretekey).parseClaimsJws(token).getBody();
		
		return claims.getSubject();  
	}
	
	public boolean validateToken(String token) {
		
		try {
			Jwts.parser().setSigningKey(SecurityConstant.secretekey).parseClaimsJws(token); 
			return true;
		}
		catch (Exception e) {

			throw new AuthenticationCredentialsNotFoundException("JWT was expired or incorrect");
		}
	}
}
