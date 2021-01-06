package com.example.demo2.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.tokens.CommentToken;

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
@RequestMapping(CommentResource.COMMENTS)
public class CommentResource {

	@Autowired
	private RepositoryPost repoPost;
	@Autowired
	private RepositoryComment repoComment;
	
    
	public static final String COMMENTS = "/comments";
	public static final String ID = "/{id}";
	
	@GetMapping
	public List<Comment> listAll(){
		return repoComment.findAll();
	}
	
	@PostMapping
	public Comment create(@RequestBody CommentDTO body){
		Long postId = body.getPostId();
		Post post = repoPost.findById(postId).orElseThrow(()-> new NotFoundException("Post id ("+ postId +")"));
		
		Comment comment = new Comment(body.getContent());
		comment = repoComment.save(comment);
		post.addComment(comment);
		repoPost.save(post);
		System.out.println(comment);
		return comment;
	}
	
	@PutMapping(value = ID)
	public Post update(@RequestBody Post body, @PathVariable Long id){
		Post post = repoPost.findById(id).orElseThrow(()-> new NotFoundException("Post id ("+ id +")"));
		post.setTitle(body.getTitle());
		
		return repoPost.save(post);
	}
	
	@DeleteMapping(value = ID)
	public Post delete(@PathVariable Long id){
		
		Comment comment = repoComment.findById(id).orElseThrow(()->new NotFoundException("Comment id ("+ id +")"));
		Post post = repoComment.findPost(id).get(0);
		
		post.getComments().remove(comment);

		return repoPost.save(post);
	}
}