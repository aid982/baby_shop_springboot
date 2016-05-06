package com.osetskiy.baby_shop.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "productitem")
public class ProductItem  extends  AbstractEntity {	
	@ManyToOne
	@JsonIgnore @XmlTransient
	private Product product;	
	private String size;
	private Integer qty;	
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	@Override
	public String toString() {	
		return ""+product.getName() +" "+size +" "+qty; 
				}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	
	

}
