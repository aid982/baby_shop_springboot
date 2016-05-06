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
import com.osetskiy.baby_shop.REPO.CategoryRepository;
import com.osetskiy.baby_shop.model.Category;

@Controller
@RequestMapping("/rest/categories")
public class CategoryControllerRest {	
	private CategoryRepository categoryRepository;	
	@Autowired	
	public CategoryControllerRest(CategoryRepository categoryRepository) {
		
		this.categoryRepository = categoryRepository;		
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public Category setCategory(@RequestBody Category sentCategory )	
	{
		try {
			Category category = categoryRepository.save(sentCategory);
			return category;
		} catch (Exception e) {
			return null;
		}								
	}
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Category> getCategoriesWithProductCount()	{		
		return (List<Category>) categoryRepository.findAllCategoriesWithCountingProducts();
				
	}
	
	@RequestMapping(value = "/{categoryId}")
	public String getProductsByCategories(@PathVariable String categoryId,@QueryParam("page") Integer page,Model model)	
	{		
		return "category/productsList";	
				
	}
	

	

}
