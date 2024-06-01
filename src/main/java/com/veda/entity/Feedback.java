package com.veda.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Feedback")
public class Feedback {

	@Id
	private int id;
	private String name;
	private String email;
	private String review;
	
	public Feedback() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Feedback(int id, String name, String email, String review) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.review = review;
	}

	public Feedback(String name, String email, String review) {
		super();
		this.name = name;
		this.email = email;
		this.review = review;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	
	
}
