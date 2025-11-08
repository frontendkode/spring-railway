package com.KissTech.crm.jwt;

import java.util.Date;
import java.util.function.Function;

import jakarta.servlet.http.HttpServletRequest;

import com.KissTech.crm.serviceimpl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.*;

@Component
public class JwtUtils {
	@Value("${bocxy.app.jwtSecret}")
	private String jwtSecret;

	@Value("${bocxy.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	private final UserDetailsServiceImpl userDetailsService;

	public JwtUtils(UserDetailsServiceImpl userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	// Generate JWT token
	public String generateJwtToken(Authentication authentication) {
		UserDetails userPrincipal = (UserDetails) authentication.getPrincipal();
		return Jwts.builder()
				.setSubject(userPrincipal.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}



	// Extract username from JWT
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// Validate the JWT token
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			System.out.println("Token expired: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("Unsupported JWT: " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("Malformed JWT: " + e.getMessage());
		} catch (SignatureException e) {
			System.out.println("Invalid signature: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument token: " + e.getMessage());
		}
		return false;
	}

	// Extract specific claim from JWT
	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return claimsResolver.apply(claims);
	}

	// Retrieve token from HTTP request header
	public String getTokenFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7);
		}
		return null;
	}

	// Get authentication object using token
	public Authentication getAuthentication(String token) {
		String username = getUsernameFromToken(token);
		UserDetails userDetails = userDetailsService.loadUserByUsername(username);
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// Extract expiration time from JWT
	public Date getExpirationTime(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	// Check if token is expired
	public boolean isTokenExpired(String token) {
		return getExpirationTime(token).before(new Date());
	}
	public boolean validateJwtToken(String token) {
		try {
			// Parse the token to check if it's valid
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true; // If no exceptions, token is valid
		} catch (ExpiredJwtException e) {
			System.out.println("JWT token is expired: " + e.getMessage());
		} catch (UnsupportedJwtException e) {
			System.out.println("JWT token is unsupported: " + e.getMessage());
		} catch (MalformedJwtException e) {
			System.out.println("JWT token is malformed: " + e.getMessage());
		} catch (SignatureException e) {
			System.out.println("JWT signature is invalid: " + e.getMessage());
		} catch (IllegalArgumentException e) {
			System.out.println("JWT token is null or empty: " + e.getMessage());
		}
		return false; // Token is invalid if an exception was caught
	}

}