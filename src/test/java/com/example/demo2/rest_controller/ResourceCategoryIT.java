package com.example.demo2.rest_controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


import com.example.demo2.ApiTestConfig;
import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Category;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryCategory;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryPost;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;


@ApiTestConfig
public class ResourceCategoryIT {
	
	@Autowired
	private RepositoryCategory repoCategory;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeEach
	void seedDb() {
		for(int i = 0; i < 2 ; i++) {
			Category category = new Category("Category "+i);
			repoCategory.save(category);
		}
	}
	@Test
	void testListAll() {		
		List<CategoryDto> categories = this.webTestClient.get().uri(CategoryResource.CATEGORIES)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(CategoryDto.class)
				.returnResult().getResponseBody();
		
		assertEquals(2, categories.size());
	}
	
	@Test
	void testSaveSuccess() {
		CategoryCreationDto categoryDto = new CategoryCreationDto("Category");
		
		CategoryDto response = this.webTestClient.post().uri(CategoryResource.CATEGORIES)
				.body(BodyInserters.fromValue(categoryDto))
				.exchange()
				.expectStatus().isOk()
				.expectBody(CategoryDto.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getCategoryName(), categoryDto.getCategoryName());
	}
	@Test
	void testSaveBadRequestNameNotBlank() {
		CategoryCreationDto categoryDto = new CategoryCreationDto("");
		
		this.webTestClient.post().uri(CategoryResource.CATEGORIES)
				.body(BodyInserters.fromValue(categoryDto))
				.exchange()
				.expectStatus().isBadRequest();
	}
	
	@Test
	void testUpdateCategoryPresent() {
	
		Long id = createCategory("CategoryUpdate");
		
		CategoryCreationDto categoryDto = new CategoryCreationDto("Category");
		CategoryDto response = this.webTestClient.put().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
				.body(BodyInserters.fromValue(categoryDto))
				.exchange()
				.expectStatus().isOk()
				.expectBody(CategoryDto.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getCategoryName(), categoryDto.getCategoryName());
	}

	@Test
	void testUpdateCategorytNotPresent() {
		int id = 666;
		CategoryCreationDto categoryDto = new CategoryCreationDto("Category");
		
		this.webTestClient.put().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
				.body(BodyInserters.fromValue(categoryDto))
				.exchange()
				.expectStatus().isNotFound();
	}
	
	@Test
	void testReadCategoryPresent() {
		Long id = createCategory("Nuevo");
		
		CategoryDto response = this.webTestClient.get().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
				.exchange()
				.expectStatus().isOk()
				.expectBody(CategoryDto.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getCategoryId(), id);
	}
	@Test
	void testReadPostNotPresent() {
		int id = 555;
		this.webTestClient.get().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
				.exchange()
				.expectStatus().isNotFound();
	}
	
	@Test
	void testDeleteCategoryPresent() {
		Long id = createCategory("Nuevo");
		
		this.webTestClient.delete().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
				.exchange()
				.expectStatus().isOk();
	}
	
	@Test
	void testCategoryNotPresent() {
		int id = 555;
		this.webTestClient.delete().uri(PostResource.POSTS+PostResource.ID, id)
				.exchange()
				.expectStatus().isOk();
	}
	
	Long createCategory(String name) {
		Category category = new Category(name);
		repoCategory.save(category);
		return category.getId();
	}

	@AfterEach
	void drowp() {
		repoCategory.deleteAll();
	}
	
	
}
