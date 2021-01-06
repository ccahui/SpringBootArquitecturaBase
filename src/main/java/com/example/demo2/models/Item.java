package com.example.demo2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Item {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "item", orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
	private List<Bid> bids = new ArrayList();
	
	
	
	public Item() {}
	public Item(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Bid> getBids() {
		return bids;
	}
	public void setBids(List<Bid> bids) {
		this.bids = bids;
	}
	
	
}
