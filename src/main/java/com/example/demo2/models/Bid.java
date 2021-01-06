package com.example.demo2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bid {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private float price;
	@ManyToOne(optional = false)
	@JoinColumn(nullable = false)
	private Item item;
	


	public Bid() {
	}
	public Bid(float price) {
		this.price = price;
		this.item = item;
	}
	
	public Bid(float price, Item item) {
		this.price = price;
		this.item = item;
	}

	public Long getId() {
		return id;
	}
	
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	
	
}
