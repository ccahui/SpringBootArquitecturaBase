package com.example.demo2.dto;


public class CategoryCreationDto {

	
	private String categoryName;
	
	public CategoryCreationDto() {}
	public CategoryCreationDto(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
}
