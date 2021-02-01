package com.example.demo2.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.example.demo2.services.PdfService;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(PostResource.POSTS)
public class PostResource {

	@Autowired
	private RepositoryPost repoPost;
    
	public static final String POSTS = "/posts";
	public static final String ID = "/{id}";
	public static final String PDF = "/pdf";
	
	@GetMapping
	public ResponseEntity<PageCustomize<Post>>listAll(@PageableDefault(page=0, size=5) Pageable pageable){
		Page<Post> pagePosts= repoPost.findAll(pageable);
		
		PageCustomize<Post> response = new PageCustomize<Post>(pagePosts);
		
		
		return new ResponseEntity<>(response, HttpStatus.OK);
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
	@GetMapping(value = PDF, produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> postReport() {

		List<Post> posts = repoPost.findAll();

        ByteArrayInputStream bis = PdfService.postReport(posts);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}