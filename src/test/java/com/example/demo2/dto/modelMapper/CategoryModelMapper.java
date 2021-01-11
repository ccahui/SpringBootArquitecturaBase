package com.example.demo2.dto.modelMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo2.TestConfig;
import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Category;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.EntitiesIT;
import com.example.demo2.repositories.RepositoryCategory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@TestConfig
public class CategoryModelMapper {

	@Autowired
	private RepositoryCategory repoCategory;
	@Autowired
	private ModelMapper modelMapper;
	
	@BeforeEach
	public void seedCategory() {
		Category category = new Category("Category 1");
		Category category2 = new Category("Category 2");
		
		Category[] categories = { category, category2 };
		
		repoCategory.saveAll(Arrays.asList(categories));
	}
	
	@Test
	@Transactional
	public void categoryToCategoryDto() {
		
		Category category = new Category("Category");
		repoCategory.save(category);
		
		CategoryDto dto = modelMapper.map(category, CategoryDto.class);
		
		assertEquals(category.getId(), dto.getCategoryId());
		assertEquals(category.getName(), dto.getCategoryName());
		
	}
	
	@Test
	@Transactional
	public void categoryCreationDtoToCategory() {
		
		CategoryCreationDto categoryCreationDto = new CategoryCreationDto("Nuevo");
		
		Category category = modelMapper.map(categoryCreationDto, Category.class);
		
		assertEquals(categoryCreationDto.getCategoryName(), category.getName());
		
		
	}
	
	

}
