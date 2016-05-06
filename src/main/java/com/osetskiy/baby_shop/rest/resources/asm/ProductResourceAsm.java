package com.osetskiy.baby_shop.rest.resources.asm;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.osetskiy.baby_shop.controller.ImageController;
import com.osetskiy.baby_shop.model.Product;
import com.osetskiy.baby_shop.rest.mvc.ProductControllerRest;
import com.osetskiy.baby_shop.rest.resources.ProductResource;

public class ProductResourceAsm extends ResourceAssemblerSupport<Product, ProductResource>{	
	public ProductResourceAsm(Class<ProductControllerRest> controllerClass, Class<ProductResource> resourceType) {
		super(controllerClass, resourceType);		
	}

	@Override
	public ProductResource toResource(Product entity) {
		ProductResource res = createResourceWithId(entity.getId(), entity);
		res.setProdId(entity.getId());
		res.setCategory(entity.getCategory());
		res.setFeatured(entity.getFeatured());
		res.setName(entity.getName());		
		res.setPrice(entity.getPrice());
		res.setProductItems(entity.getProductItems());
		res.setForBoys(entity.getForBoys());
		res.setForGirls(entity.getForGirls());
		res.setDescription(entity.getDescription());
		res.setKeywords(entity.getKeywords());
		
		Link linkPictureLarge = linkTo(ImageController.class).slash("/resources/images/_" +entity.getId() +"_large.jpg").withRel("pictLarge");
		Link linkPictureSmall = linkTo(ImageController.class).slash("/resources/images/_" +entity.getId() +"_small.jpg").withRel("pictSmall");
		Link linkPicture = linkTo(ImageController.class).slash("/resources/images/_" +entity.getId() +".jpg").withRel("pict");/*Link linkPictureSmall = linkTo(ImageController.class).slash("/productImage/"+entity.getId()+"/").withRel("pictSmall");
		Link linkPicture = linkTo(ImageController.class).slash("/productImage/"+entity.getId()+"/normal").withRel("pict");*/
		res.add(linkPictureLarge,linkPictureSmall,linkPicture);
		return res;
	}
	
	
}
