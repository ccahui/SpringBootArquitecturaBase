package com.example.demo2.dto;

import javax.validation.constraints.NotBlank;

public class CategoryCreationDto {

	@NotBlank
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
