package com.example.demo2.dto.modelMapper;

import org.modelmapper.PropertyMap;

import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.models.Category;

public class CategoryCreationDtoToCategory extends  PropertyMap<CategoryCreationDto, Category> {

	@Override
	protected void configure() {
		  map().setName(source.getCategoryName());
	}

}
