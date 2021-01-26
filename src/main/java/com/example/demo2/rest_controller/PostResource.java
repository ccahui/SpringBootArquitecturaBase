package com.example.demo2.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.example.demo2.models.Comment;
import com.example.demo2.models.Comment2;
import com.example.demo2.models.Post;
import com.example.demo2.models.Post2;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryComment2;
import com.example.demo2.repositories.RepositoryPost;
import com.example.demo2.repositories.RepositoryPost2;
import com.example.demo2.rest_controller.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(PostResource.POSTS)
public class PostResource {

	@Autowired
	private RepositoryPost repoPost;
    
	public static final String POSTS = "/posts";
	public static final String ID = "/{id}";
	
	@GetMapping
	public List<Post> listAll(){
		return repoPost.findAll();
	}
	
	@PostMapping
	public Post create(@RequestBody Post body){
		return repoPost.save(body);
	}
	
	@GetMapping(value = ID)
	public Post read(@PathVariable Long id){
		Post post = repoPost.findById(id).orElseThrow(()-> new NotFoundException("Post id ("+ id +")"));
		return post;
	}
	
	@PutMapping(value = ID)
	public Post update(@RequestBody Post body, @PathVariable Long id){
		Post post = repoPost.findById(id).orElseThrow(()-> new NotFoundException("Post id ("+ id +")"));
		post.setTitle(body.getTitle());
		
		return repoPost.save(post);
	}
	
	@DeleteMapping(value = ID)
	public void delete(@PathVariable Long id){
		 if (this.repoPost.findById(id).isPresent()) {
	            this.repoPost.deleteById(id);
	        }
		
	}
}