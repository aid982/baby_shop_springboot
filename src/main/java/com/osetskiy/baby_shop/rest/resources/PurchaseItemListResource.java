package com.osetskiy.baby_shop.rest.resources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.osetskiy.baby_shop.model.PurchaseItem;

public class PurchaseItemListResource extends ResourceSupport{
	private List<PurchaseItem>  listOfProducts = new ArrayList<>();

	public List<PurchaseItem> getlistOfProducts() {
		return listOfProducts;
	}
	public void setPurchaseItemList(List<PurchaseItem> purchaseItemList) {
		this.listOfProducts = purchaseItemList;
	}
	@JsonIgnore	
	public Integer getOrderTotal(){
		Iterator<PurchaseItem> iterator = listOfProducts.iterator();
		Integer sum=0;
		while(iterator.hasNext()){			
			PurchaseItem pItem = iterator.next();
			sum=sum+pItem.getPrice()*pItem.getQty();
		}
		return sum;
	}
	
	public PurchaseItemListResource(){
		
	}
	public PurchaseItemListResource(String stringJSON) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		this.listOfProducts = mapper.readValue(stringJSON, new TypeReference<List<PurchaseItem>>(){});		
//		List<PurchaseItemResource> myObjects = Arrays.asList(mapper.readValue(stringJSON, PurchaseItemResource[].class));
		
		
	}	
	
	
	
}
