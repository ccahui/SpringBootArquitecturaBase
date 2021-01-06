package com.example.demo2.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CategoryItem {

	public static final String CATEGORY_ID = "CATEGORY_ID";
	public static final String ITEM_ID = "ITEM_ID";

	@EmbeddedId
	private IdCategoryItem id = new IdCategoryItem();

	@ManyToOne
	@JoinColumn(name = ITEM_ID, insertable = false, updatable = false)
	protected ItemM item;
	@ManyToOne
	@JoinColumn(name = CATEGORY_ID, insertable = false, updatable = false)
	private Category category;
	

	@Column(updatable = false, nullable = false)
	private Date createAt = new Date();

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	
	public CategoryItem() {}

	public CategoryItem(Category category, ItemM item) {
		this.category = category;
		this.item = item;
		this.id.categoryId = category.getId();
		this.id.itemId = item.getId();
		//Sincronizando las asociaciones bidireccionales
		category.getCategoryItems().add(this);
		item.getCategoryItems().add(this);
	}

	public IdCategoryItem getId() {
		return id;
	}

	public void setId(IdCategoryItem id ) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public ItemM getItem() {
		return item;
	}

	public void setItem(ItemM item) {
		this.item = item;
	}


	public int hashCode() {
		return id.hashCode()+item.hashCode();
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof CategoryItem) {
			CategoryItem that = (CategoryItem) o;
			return id.equals(that.getId());
		}
		return false;
	}

	public String toString() {
		String format = "CategoryItem[category=%s, item='%s', ] ";
		return String.format(format, category, item);
	}
}
