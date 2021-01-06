package com.example.demo2.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class ItemM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	@OneToMany(mappedBy = "item")
	protected Set<CategoryItem> categoryItems = new HashSet();
	
	public Set<CategoryItem> getCategoryItems() {
		return categoryItems;
	}
	public void setCategoryItems(Set<CategoryItem> categoryItem) {
		this.categoryItems = categoryItem;
	}
	
	public ItemM() {}
	public ItemM(String name) {
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
	@Override
	public String toString() {
		String format = "Item M[id=%d, name='%s'] ";
		return String.format(format, id, name);
	}
	
	
}
