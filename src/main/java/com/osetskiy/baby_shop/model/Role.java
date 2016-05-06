package com.osetskiy.baby_shop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Role extends AbstractEntity {	
	@ManyToMany(mappedBy="roles")
	private List<User> users = new ArrayList<>();	
	
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.getId().toString();
	}
	public Role(String role) {
		super.setId(role);		
	}
	public Role() {
	
	}

}
