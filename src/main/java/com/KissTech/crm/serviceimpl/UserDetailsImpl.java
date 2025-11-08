package com.KissTech.crm.serviceimpl;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.KissTech.crm.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private UUID id;

	private String username;

	@JsonIgnore
	private String password;
	
	private String role;
		

	private User user; // Add this line to define the user object

	public UserDetailsImpl(UUID id, String username, String password, String role,User user) {
		// TODO Auto-generated constructor stub
		this.id = id;
		this.username = username;
		this.password = password;
		this.user = user;
		this.role=role;

	}

	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(user.getId(), user.getUsername(), user.getPassword(), user.getRole(),user);
	}

	private Collection<? extends GrantedAuthority> authorities;

	// Other fields and constructors

	// Modify the getAuthorities() method to handle null case
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (authorities == null) {
			return Collections.emptyList(); // Return an empty list if authorities is null
		}
		return authorities;
	}

	public UUID getId() {
		return user != null ? user.getId() : null;
	}

	

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	public String getRole() {
		// TODO Auto-generated method stub
		return role;
	}



}
