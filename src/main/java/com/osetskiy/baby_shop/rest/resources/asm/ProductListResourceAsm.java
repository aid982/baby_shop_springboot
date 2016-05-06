package com.osetskiy.baby_shop.rest.resources.asm;

import java.util.List;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.osetskiy.baby_shop.rest.mvc.ProductControllerRest;
import com.osetskiy.baby_shop.rest.resources.ProductListResource;
import com.osetskiy.baby_shop.rest.resources.ProductResource;
import com.osetskiy.baby_shop.rest.utils.ProductList;

public class ProductListResourceAsm extends ResourceAssemblerSupport<ProductList, ProductListResource>{

	public ProductListResourceAsm(Class<ProductControllerRest> controllerClass, Class<ProductListResource> resourceType) {
		super(controllerClass, resourceType);	
	}

	@Override
	
	public ProductListResource toResource(ProductList entity) {
		List<ProductResource> tmpResource = new ProductResourceAsm(ProductControllerRest.class, ProductResource.class).toResources(entity.getProducts());
		ProductListResource resource = new ProductListResource();
		resource.setProducts(tmpResource);
		return resource;
	}
	

}
