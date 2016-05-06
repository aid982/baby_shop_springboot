package com.osetskiy.baby_shop.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;





@Entity
@Table(name = "purchase_order")
@XmlRootElement
@JsonRootName(value="order")
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	@Column(name ="orderdate")
    private Date orderDate;
	@ManyToOne
	private User customer;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.EAGER)
	@JsonIgnore
    private List<PurchaseItem> purchaseItems = new ArrayList<PurchaseItem>();
	
	private Boolean processed;
	
	
	public Boolean getProcessed() {
		return processed;
	}
	public void setProcessed(Boolean processed) {
		this.processed = processed;
	}
	
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
	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}
	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getId()+" "+customer.getName();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	
	

}
