package com.example.demo2.services;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.CommentToken;

import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Category;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Comment2;
import com.example.demo2.models.Post;
import com.example.demo2.models.Post2;
import com.example.demo2.repositories.RepositoryCategory;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryComment2;
import com.example.demo2.repositories.RepositoryPost;
import com.example.demo2.repositories.RepositoryPost2;
import com.example.demo2.rest_controller.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService {

	@Autowired
	private RepositoryCategory repoCategory;
	@Autowired ModelMapper modelMapper;

	public List<CategoryDto> listAll() {
		List<Category> categories = repoCategory.findAll();
		List<CategoryDto> categoriesDto = categories.stream().map(category  -> categoryToCategoryDto(category))
		          .collect(Collectors.toList());
		return categoriesDto;
	}

	public CategoryDto create(CategoryCreationDto body) {

		Category category = categoryCreationDtoToCategory(body);
		repoCategory.save(category);
		CategoryDto dto = categoryToCategoryDto(category);

		return dto;
	}

	public CategoryDto update(Long id, CategoryCreationDto body) {

		Category category = repoCategory.findById(id)
				.orElseThrow(() -> new NotFoundException("Category id (" + id + ")"));
		category.setName(body.getCategoryName());
		repoCategory.save(category);

		CategoryDto dto = categoryToCategoryDto(category);
		return dto;
	}

	public CategoryDto read(Long id){
		Category category = repoCategory.findById(id).orElseThrow(()-> new NotFoundException("Category id ("+ id +")"));
		CategoryDto dto = categoryToCategoryDto(category);
		return dto;
	}

	public void delete(Long id) {
		Optional<Category> category = repoCategory.findById(id);
		if (category.isPresent()) {
			repoCategory.deleteById(id);
		}
	}

	private Category categoryCreationDtoToCategory(CategoryCreationDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);
		return category;
	}

	private CategoryDto categoryToCategoryDto(Category category) {
		CategoryDto dto = modelMapper.map(category, CategoryDto.class);
		return dto;
	}

}