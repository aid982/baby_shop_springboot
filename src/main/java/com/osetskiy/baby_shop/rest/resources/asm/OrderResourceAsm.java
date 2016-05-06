package com.osetskiy.baby_shop.rest.resources.asm;

import java.util.List;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import com.osetskiy.baby_shop.model.Order;
import com.osetskiy.baby_shop.model.PurchaseItem;
import com.osetskiy.baby_shop.rest.mvc.OrderControllerRest;
import com.osetskiy.baby_shop.rest.resources.OrderResource;


public class OrderResourceAsm extends ResourceAssemblerSupport<Order, OrderResource>{	
	public OrderResourceAsm(Class<OrderControllerRest> controllerClass, Class<OrderResource> resourceType) {
		super(controllerClass, resourceType);		
	}

	@Override
	public OrderResource toResource(Order order) {
		OrderResource res = new OrderResource();
		res.setProcessed(order.getProcessed());
		res.setOrderID(order.getId());
		res.setCustomer(order.getCustomer());
		res.setOrderDate(order.getOrderDate());		
		List<PurchaseItem> purchItem = order.getPurchaseItems();
		res.getPurchaseItemList().setPurchaseItemList(purchItem);		
		return res;
	}
	
	
}
