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


@Embeddable
public class IdCategoryItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name = CategoryItem.CATEGORY_ID)
	protected Long categoryId;
	@Column(name = CategoryItem.ITEM_ID)
	protected Long itemId;

	public IdCategoryItem() {
	}

	public IdCategoryItem(Long categoryId, Long itemId) {
		this.categoryId = categoryId;
		this.itemId = itemId;
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof IdCategoryItem) {
			IdCategoryItem that = (IdCategoryItem) o;
			return this.categoryId.equals(that.categoryId) && this.itemId.equals(that.itemId);
		}
		return false;
	}

	public int hashCode() {
		return categoryId.hashCode() + itemId.hashCode();
	}
	
	public String toString() {
		String format = "IdCategoryItem[idCategory=%d, idItem='%d', ] ";
		return String.format(format, categoryId, itemId);
	}
}