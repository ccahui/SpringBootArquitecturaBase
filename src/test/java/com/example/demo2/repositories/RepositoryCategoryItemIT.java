package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
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
import com.example.demo2.repositories.RepositoryCategoryItem;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryItemM;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;

@TestConfig
public class RepositoryCategoryItemIT {
	
	@Autowired
	private RepositoryItemM repoItem;
	@Autowired
	private RepositoryCategory repoCategory;
	@Autowired RepositoryCategoryItem repoCategoryItem;

	
	@Test
	@Transactional
	void testCreateItemCategory() {
		
		ItemM item = new ItemM("Samsumg X");
		Category category = new Category("Phone");
		repoCategory.save(category);
		repoItem.save(item);
		
		CategoryItem categoryItem = new CategoryItem(category, item);
		repoCategoryItem.save(categoryItem);

		assertEquals(1, item.getCategoryItems().size());
		assertEquals(1, category.getCategoryItems().size());
		assertEquals(1, repoItem.count());
		assertEquals(1, repoCategory.count());
		assertEquals(1, repoCategoryItem.count());
		
	}
	
	@Test
	@Transactional
	void testDeleteCategoryItem() {
		IdCategoryItem id = createCategoryItem();
		CategoryItem categoryItem = repoCategoryItem.findById(id).get();
		
		Category category = categoryItem.getCategory();
		ItemM item = categoryItem.getItem();
		
		category.getCategoryItems().remove(categoryItem);
		item.getCategoryItems().remove(categoryItem);
	
		repoCategoryItem.delete(categoryItem);
		
		
		category = repoCategory.findById(category.getId()).get();
		item = repoItem.findById(item.getId()).get();
		
		assertEquals(0, repoCategoryItem.count());
		assertEquals(0,category.getCategoryItems().size());
		assertEquals(0,item.getCategoryItems().size());
	}


	IdCategoryItem createCategoryItem() {
		ItemM item = new ItemM("Samsumg X");
		Category category = new Category("Phone");
		repoCategory.save(category);
		repoItem.save(item);
		
		CategoryItem categoryItem = new CategoryItem(category, item);
		repoCategoryItem.save(categoryItem);
		
		return categoryItem.getId();
	}
	
	
	
	
	
}
