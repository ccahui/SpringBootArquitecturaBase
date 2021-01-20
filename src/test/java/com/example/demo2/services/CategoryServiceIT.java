package com.example.demo2.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Bid;
import com.example.demo2.models.Category;
import com.example.demo2.models.CategoryItem;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Course;
import com.example.demo2.models.CourseMaterial;
import com.example.demo2.models.IdCategoryItem;
import com.example.demo2.models.Item;
import com.example.demo2.models.ItemM;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryCategory;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryCourseIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;

@TestConfig
public class CategoryServiceIT {
	
	@Autowired
	private RepositoryCategory repoCategory;
	@Autowired ServiceCategoria service;

	@Test
	@Transactional
	void testListAll() {
		
		createCategory();
		List<CategoryDto> categoriesDto = service.listAll();
		assertEquals(1, repoCategory.count());
		assertNotNull(categoriesDto.get(0).getCategoryId());
		
	}
	@Test
	@Transactional
	void testCreate() {
		
		System.out.println(repoCategory.findAll());
		CategoryCreationDto category = new CategoryCreationDto("NewCategory");
		CategoryDto dto = service.create(category);
		
		assertEquals(1, repoCategory.count());
		assertEquals(category.getCategoryName(), dto.getCategoryName());
		assertNotNull(dto.getCategoryId());;
		
	}
	
	@Test
	@Transactional
	void testUpdate() {
		
		Long id = createCategory();
		
		CategoryCreationDto category = new CategoryCreationDto("UpdateCategory");
		CategoryDto dto = service.update(id, category);
		
		assertEquals(1, repoCategory.count());
		assertEquals(category.getCategoryName(), dto.getCategoryName());
		assertEquals(id, dto.getCategoryId());
		
	}
	
	@Test
	@Transactional
	void testDeleteCategoryItem() {
		
		Long id = createCategory();
		service.delete(id);
		assertEquals(0, repoCategory.count());
	}

	Long createCategory() {
	
		Category category = new Category("Phone");
		repoCategory.save(category);
		return category.getId();
	}
	
	
	
	
	
}
