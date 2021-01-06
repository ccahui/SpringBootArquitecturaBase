package com.example.demo2.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;	
	@OneToMany(mappedBy = "category")
	protected Set<CategoryItem> categoryItems = new HashSet();
	
	
	public Set<CategoryItem> getCategoryItems() {
		return categoryItems;
	}
	public void setCategoryItems(Set<CategoryItem> categoryItem) {
		this.categoryItems = categoryItem;
	}
	public Category() {}
	public Category(String name) {
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
		String format = "Category [id=%d, name='%s'] ";
		return String.format(format, id, name);
	}
	
	
}
