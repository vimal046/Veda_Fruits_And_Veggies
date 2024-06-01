package com.veda.entity;

import java.util.List;

public class FormObject {

	
	private int formId;
	private OrderForm formObj;
	private List<OrderItem> itemObj;

	public FormObject() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FormObject(OrderForm formObj, List<OrderItem> itemObj) {
		super();
		this.formObj = formObj;
		this.itemObj = itemObj;
	}

	public FormObject(int formId, OrderForm formObj, List<OrderItem> itemObj) {
		super();
		this.formId = formId;
		this.formObj = formObj;
		this.itemObj = itemObj;
	}

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public OrderForm getFormObj() {
		return formObj;
	}

	public void setFormObj(OrderForm formObj) {
		this.formObj = formObj;
	}

	public List<OrderItem> getItemObj() {
		return itemObj;
	}

	public void setItemObj(List<OrderItem> itemObj) {
		this.itemObj = itemObj;
	}

}
