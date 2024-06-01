package com.veda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="OrderForm")
public class OrderForm {
   
	@Id
	private int orderId;
	private String fName;
	private String lName;
	private String cName;
	private String street;
	private String city;
	private String country;
	private String postcode;
	private String mobile;
	private String email;
	private String notes;
	private int grandTotal;
	
	public OrderForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderForm(int orderId, String fName, String lName, String cName, String street, String city, String country,
			String postcode, String mobile, String email, String notes, int grandTotal) {
		super();
		this.orderId = orderId;
		this.fName = fName;
		this.lName = lName;
		this.cName = cName;
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
		this.mobile = mobile;
		this.email = email;
		this.notes = notes;
		this.grandTotal = grandTotal;
	}

	public OrderForm(String fName, String lName, String cName, String street, String city, String country,
			String postcode, String mobile, String email, String notes, int grandTotal) {
		super();
		this.fName = fName;
		this.lName = lName;
		this.cName = cName;
		this.street = street;
		this.city = city;
		this.country = country;
		this.postcode = postcode;
		this.mobile = mobile;
		this.email = email;
		this.notes = notes;
		this.grandTotal = grandTotal;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public int getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(int grandTotal) {
		this.grandTotal = grandTotal;
	}

}
