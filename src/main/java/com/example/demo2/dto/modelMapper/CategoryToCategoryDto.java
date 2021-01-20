package com.example.demo2.dto.modelMapper;

import org.modelmapper.PropertyMap;

import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Category;

public class CategoryToCategoryDto extends PropertyMap<Category, CategoryDto> {
	@Override
	protected void configure() {
		  map().setCategoryId(source.getId());
		  map().setCategoryName(source.getName());
	}

}
