package com.osetskiy.baby_shop.rest.utils;
import com.osetskiy.baby_shop.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductList {
	private List<Product> products = new ArrayList<>();
	

	public ProductList(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	

}
