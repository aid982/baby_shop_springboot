package com.osetskiy.baby_shop.model;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@XmlRootElement
public class Product  extends AbstractEntity{	
	private String name;
	private int price;
	private Boolean featured;
	private Boolean forBoys;
	private Boolean forGirls;
	//@JsonIgnore @XmlTransient
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy="product")	
	private Set<ProductItem> productItems = new HashSet<ProductItem>();
	private String description;
	private String keywords;	
	
	
	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getKeywords() {
		return keywords;
	}


	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}


	public Boolean getForBoys() {
		return forBoys;
	}


	public void setForBoys(Boolean forBoys) {
		this.forBoys = forBoys;
	}


	public Boolean getForGirls() {
		return forGirls;
	}


	public void setForGirls(Boolean forGirls) {
		this.forGirls = forGirls;
	}


	public Set<ProductItem> getProductItems() {
		return productItems;
	}


	public void setProductItems(Set<ProductItem> productItems) {
		this.productItems = productItems;
	}



	@ManyToOne	
	private Category category;
	
	
	public String getName() {
		return name;
	}	

	
	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price/100;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
	//@JsonIgnore @XmlTransient
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}



	@Override
	public String toString() {		
		return name;
	}



	public Boolean getFeatured() {
		return featured;
	}



	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	
	

	

}
