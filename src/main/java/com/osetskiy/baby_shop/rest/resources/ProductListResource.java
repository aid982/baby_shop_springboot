package com.osetskiy.baby_shop.rest.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

public class ProductListResource extends ResourceSupport{
	private List<ProductResource> products = new ArrayList<>();

	public List<ProductResource> getProducts() {
		return products;
	}

	public void setProducts(List<ProductResource> products) {
		this.products = products;
	}

	public ProductListResource() {
		
	}
	
public ProductListResource(String stringJSON) {
	System.out.println(stringJSON);
		
	}
	
	

}
