package com.osetskiy.baby_shop.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.osetskiy.baby_shop.model.User;

public class AccountResource extends ResourceSupport {
	
	private String name;	
	private String email;	
	private String firstName;	
	private String lastName;	
	private String password;
	private String phone;
	private Boolean isAdmin;
	
	
	
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@JsonIgnore
	public String getPassword() {
		return password;
	}
	@JsonProperty
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public User toUser(){
		User user = new User();
		user.setName(name);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);		
		user.setPassword(hashedPassword);
		user.setPhone(phone);		
		return user;
		
	}
	
	
	

}
