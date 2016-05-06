package com.osetskiy.baby_shop.rest.mvc;

import java.util.List;
import javax.ws.rs.QueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.osetskiy.baby_shop.REPO.ProductItemRepository;
import com.osetskiy.baby_shop.model.ProductItem;

@Controller
@RequestMapping("/rest/product_item")
public class ProductItemControllerRest {	
	private ProductItemRepository productItemRepository;	
	@Autowired	
	public ProductItemControllerRest(ProductItemRepository productItemRepository) {
		
		this.productItemRepository =productItemRepository;	
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public ProductItem setProductItem(@RequestBody ProductItem sentProductItem)	
	{
		try {
			ProductItem productItem = productItemRepository.save(sentProductItem);
			return productItem;
		} catch (Exception e) {
			return null;
		}								
	}
	
	@RequestMapping(value="/sizes",method = RequestMethod.GET)
	@ResponseBody
	public List<ProductItem> getProductItems()	
	{		
		List<ProductItem> prodList = (List<ProductItem>)productItemRepository.findAllGroupBySizes();
		System.out.println(prodList);
		return prodList;	
				
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<ProductItem> getAllProductItemsSizes()	
	{		
		return (List<ProductItem>)productItemRepository.findAll();	
				
	}
	
	@RequestMapping(value = "/{productItemId}")
	@ResponseBody
	public ProductItem getProductsById(@PathVariable String productItemId)	
	{		
		return productItemRepository.findOne(productItemId);	
				
	}
	
		

	

}
