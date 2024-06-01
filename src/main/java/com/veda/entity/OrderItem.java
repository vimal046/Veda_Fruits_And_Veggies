package com.veda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "OrderItem")
public class OrderItem {

	@Id
	private int itemId;
	private String productName;
	private int price;
	private int quantity;
	private int orderId;
	
	
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public OrderItem(int itemId, String productName, int price, int quantity, int orderId) {
		super();
		this.itemId = itemId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.orderId = orderId;
	}



	public OrderItem(String productName, int price, int quantity) {
		super();
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}



	public int getItemId() {
		return itemId;
	}



	public void setItemId(int itemId) {
		this.itemId = itemId;
	}



	public String getProductName() {
		return productName;
	}



	public void setProductName(String productName) {
		this.productName = productName;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	public int getOrderId() {
		return orderId;
	}



	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	
}
