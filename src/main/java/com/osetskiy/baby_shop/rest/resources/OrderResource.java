package com.osetskiy.baby_shop.rest.resources;
import java.util.Date;
import java.util.Iterator;
import org.springframework.hateoas.ResourceSupport;
import com.osetskiy.baby_shop.model.Order;
import com.osetskiy.baby_shop.model.PurchaseItem;
import com.osetskiy.baby_shop.model.User;


/**
 * @author osetskiy
 *
 */
public class OrderResource extends ResourceSupport {
	private Date orderDate;
	private User customer;
	private Integer orderID;
	private Boolean processed;
	
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public Integer getOrderTotal() {		
		return purchaseItemList.getOrderTotal();
	}
	
	private PurchaseItemListResource purchaseItemList = new PurchaseItemListResource();
	
	//private List<PurchaseItemResource> purchaseItemList;
	
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	
	public User getCustomer() {
		return customer;
	}
	public void setCustomer(User customer) {
		this.customer = customer;
	}
	public PurchaseItemListResource getPurchaseItemList() {
		return purchaseItemList;
	}
	public void setPurchaseItemList(PurchaseItemListResource purchaseItemList) {
		this.purchaseItemList = purchaseItemList;
	}
	
	
	public Order toOrder(){
		Order order= new Order();
		order.setProcessed(this.processed);
		order.setCustomer(this.customer);
		order.setOrderDate(this.orderDate);		
		order.setPurchaseItems(purchaseItemList.getlistOfProducts());
		Iterator<PurchaseItem> iterator = order.getPurchaseItems().iterator();
		 while(iterator.hasNext()) {
			 PurchaseItem item = iterator.next();
			 item.setOrder(order);					 
		 }		
		return order;
	}
	
	

}
