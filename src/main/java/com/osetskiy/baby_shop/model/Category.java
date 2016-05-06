package com.osetskiy.baby_shop.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@XmlRootElement
public class Category extends AbstractEntity {		
	private String name;
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy="category")	
	private Set<Product> products = new HashSet<Product>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	@JsonIgnore @XmlTransient
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
	

}
