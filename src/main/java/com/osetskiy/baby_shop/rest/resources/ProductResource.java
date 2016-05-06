package com.osetskiy.baby_shop.rest.resources;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.springframework.hateoas.ResourceSupport;

import com.osetskiy.baby_shop.model.Category;
import com.osetskiy.baby_shop.model.Product;
import com.osetskiy.baby_shop.model.ProductItem;

public class ProductResource extends ResourceSupport{
	private String ProdId;
	private String name;
	private int price;
	private Boolean featured;	
	private Boolean forBoys;
	private String description;
	private String keywords;
	private Boolean forGirls;
	private Category category;	
	
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
		
	
	private Set<ProductItem> productItems = new HashSet<ProductItem>(); 
	
	public Set<ProductItem> getProductItems() {
		return productItems;
	}
	public void setProductItems(Set<ProductItem> productItems) {
		this.productItems = productItems;
	}
	public String getProdId() {
		return ProdId;
	}
	public void setProdId(String prodId) {
		ProdId = prodId;
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
	public Boolean getFeatured() {
		return featured;
	}
	public void setFeatured(Boolean featured) {
		this.featured = featured;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public Product toProduct(){
		Product product = new Product();
		product.setCategory(this.category);
		product.setId(this.ProdId);
		product.setName(this.name);
		product.setPrice(this.price);
		product.setProductItems(this.getProductItems());
		
		Iterator<ProductItem> iterator =product.getProductItems().iterator();
		while(iterator.hasNext()){
			ProductItem item = iterator.next();
			item.setProduct(product);			
		}		
		product.setForBoys(this.forBoys);
		product.setForGirls(this.forGirls);
		product.setFeatured(this.featured);
		product.setKeywords(this.keywords);
		product.setDescription(this.description);		
		return product; 
		
	}
	public ProductResource() {		
	}
	

	
	
	
	
	
	

}
