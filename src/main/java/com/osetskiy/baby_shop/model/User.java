package com.osetskiy.baby_shop.model;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;


import org.hibernate.validator.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user_table")
@XmlRootElement
public class User extends AbstractEntity {
	
		
	@Size(max = 100, min = 5)
	private String name;

	@NotNull
	@Email
	private String email;
	@Column(name="firstname")
	private String firstName;

	@Column(name="lastname")
	private String lastName;
	@NotNull
	private String password;

	@Size(max = 20)
	private String phone;
	@ManyToMany
	@JoinTable
	@JsonIgnore
	private List<Role> roles = new  ArrayList<>();

	
	public User() {		
	}

	public User(User user) {
		this.setId(user.getId());
		this.email = user.getEmail();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password  = user.getPassword();
		this.roles     = user.getRoles();
		this.phone     = user.getPhone();
		this.name      = user.getName();

	}
	
	public String[] getRolesAsStrings(){
		String[] arrayRoles = new String[roles.size()];
		for (int i = 0; i < roles.size(); i++) {
			arrayRoles[i] = roles.get(i).toString();		
		}		
		return arrayRoles;
		
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@JsonIgnore
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return name;
	}
	@PrePersist
	private void ensureId(){
	    this.setId(UUID.randomUUID().toString());
	}
	
	public Boolean hasRole(String roleName) {
		Iterator<Role> iterator =this.getRoles().iterator();
		while(iterator.hasNext()){
			Role role=iterator.next();			
			if((role!=null)&&(role.getId().equals(roleName))) {
				return true;				
			}
		}
		return false;		
	}
	
	public Boolean isAdmin() {
		Iterator<Role> iterator =this.getRoles().iterator();
		while(iterator.hasNext()){
			Role role=iterator.next();			
			if((role!=null)&&(role.getId().equals("ROLE_ADMIN"))) {
				return true;				
			}
		}
		return false;		
	}
	
}
