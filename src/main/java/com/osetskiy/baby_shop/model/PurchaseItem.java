package com.osetskiy.baby_shop.model;



import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.springframework.hateoas.Link;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.osetskiy.baby_shop.controller.ImageController;

@Entity
@XmlRootElement
@Table(name="purchaseitem")
public class PurchaseItem extends AbstractEntity {	
	@Version
    @Column(name = "version")	
    private Integer version;
	@NotNull
	@Column(name="prodId")
	private String prodId;
	private String size;	
	
	public String getPictureRef() {
		Link linkPicture = linkTo(ImageController.class).slash("/productImage/"+this.prodId+"/small").withRel("pictSmall");		
		return linkPicture.getHref();
	}
	
	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	private Integer qty;
	private Integer price;	
	@ManyToOne(fetch=FetchType.EAGER)
	private Order order;	

	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	@JsonIgnore @XmlTransient
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}	
	
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@PrePersist
	private void ensureId(){
	    this.setId(UUID.randomUUID().toString());
	}
	
	
	

}
