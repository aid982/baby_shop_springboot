package com.osetskiy.baby_shop.rest.resources;

import org.springframework.hateoas.ResourceSupport;


public class PurchaseItemResource extends ResourceSupport{
	private String prodId;
	private String name;
	private int price;
	private int qty;
	private String size;
	private String pictureLink;
	
	
	public String getPictureLink() {
		return pictureLink;
	}
	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
//	
	public PurchaseItemResource() {		
	}
	
	

}
