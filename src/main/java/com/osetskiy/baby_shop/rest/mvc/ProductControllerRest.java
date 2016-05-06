package com.osetskiy.baby_shop.rest.mvc;


import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.osetskiy.baby_shop.REPO.CategoryRepository;
import com.osetskiy.baby_shop.REPO.ProductRepository;
import com.osetskiy.baby_shop.model.Product;
import com.osetskiy.baby_shop.rest.resources.ProductListResource;
import com.osetskiy.baby_shop.rest.resources.ProductResource;
import com.osetskiy.baby_shop.rest.resources.asm.ProductListResourceAsm;
import com.osetskiy.baby_shop.rest.resources.asm.ProductResourceAsm;
import com.osetskiy.baby_shop.rest.utils.ProductList;

@Controller
@RequestMapping("/rest/products")
public class ProductControllerRest {
	
	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;
	//@Autowired
	//ServletContext context;
	
	@Autowired
	public ProductControllerRest(ProductRepository productRepository,CategoryRepository categoryRepository) {		
		this.productRepository = productRepository;
		this.categoryRepository =categoryRepository;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<ProductListResource> setProduct(@RequestBody ProductListResource productListResource){
		Iterator<ProductResource> iterator =productListResource.getProducts().iterator();
		while(iterator.hasNext()){
			ProductResource productResource =iterator.next();
			Product product =productResource.toProduct();
			if(!categoryRepository.exists(product.getCategory().getId())){
				categoryRepository.save(product.getCategory());
			}
			
			productRepository.save(product); 
		}
		return  new ResponseEntity<ProductListResource>(productListResource,HttpStatus.OK);		
	}
	
	@RequestMapping(value = "/{id}")	
	public  ResponseEntity<ProductResource> getProduct(@PathVariable("id") String productId)
	{
		Product product =productRepository.findOne(productId);
		if(product!=null) {										 
			 //String urlPicture = context.getContextPath()+"/resources/images/_" + productId + "_small.jpg";			
			ProductResource resource = new ProductResourceAsm(ProductControllerRest.class, ProductResource.class).toResource(product);
			return new ResponseEntity<ProductResource>(resource,HttpStatus.OK);
			
		} else
		{	
		return new ResponseEntity<ProductResource>(HttpStatus.NOT_FOUND);
		}
				
	}
	@RequestMapping("")	
	public  ResponseEntity<ProductListResource> getAllProducts()
	{
		List<Product> productList =(List<Product>) productRepository.findAll();
		if(productList!=null){
			ProductList productListTMP = new ProductList(productList);			
			ProductListResource resource = new ProductListResourceAsm(ProductControllerRest.class, ProductListResource.class).toResource(productListTMP);
			return new ResponseEntity<ProductListResource>(resource,HttpStatus.OK);			
		} else{
			return new ResponseEntity<ProductListResource>(HttpStatus.NOT_FOUND);
		}	
				
	}	
	
	
}
