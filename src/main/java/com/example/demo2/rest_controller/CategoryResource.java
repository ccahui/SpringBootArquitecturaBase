package com.example.demo2.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.CommentToken;

import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Comment2;
import com.example.demo2.models.Post;
import com.example.demo2.models.Post2;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryComment2;
import com.example.demo2.repositories.RepositoryPost;
import com.example.demo2.repositories.RepositoryPost2;
import com.example.demo2.rest_controller.exception.NotFoundException;
import com.example.demo2.services.ServiceCategoria;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping(CategoryResource.CATEGORIES)
public class CategoryResource {

	@Autowired
	private ServiceCategoria service;
    
	public static final String CATEGORIES = "/categories";
	public static final String ID = "/{id}";

	@GetMapping
	public List<CategoryDto> listAll(){
		return service.listAll();
	}
	
	@PostMapping
	public CategoryDto create(@Valid @RequestBody CategoryCreationDto body){
		return service.create(body);
	}
	
	@GetMapping(value = ID)
	public CategoryDto read(@PathVariable Long id){
		return service.read(id);
	}
	
	@PutMapping(value = ID)
	public CategoryDto update(@RequestBody CategoryCreationDto body, @PathVariable Long id){
		return service.update(id, body);
	}
	
	@DeleteMapping(value = ID)
	public void delete(@PathVariable Long id){
		service.delete(id);
	}
}