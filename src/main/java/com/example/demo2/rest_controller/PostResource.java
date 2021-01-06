package com.example.demo2.rest_controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private RepositoryComment repoComment;
	
	@Autowired
	private RepositoryPost2 repoPost2;
	@Autowired
	private RepositoryComment2 repoComment2;
    
	public static final String POSTS = "/posts";
	public static final String ID = "/{id}";
	
	
	public static final String STATE = "/state";
	public static final String STATE_1 = "/state1";
	public static final String SEARCH = "/search";


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

    @GetMapping(value = STATE)
    public String readState() {
    	
      	Post post = new Post("Title POST");  

    	Comment comment = new Comment("Comment 01");
    	Comment comment1 = new Comment("Comment 01");
    	Comment comment2 = new Comment("Comment 01");
  
    	post.addComment(comment);    	
    	post.addComment(comment1);    	
    	post.addComment(comment2);
    	
    	repoPost.save(post);
    	
    	Post postLazy = repoPost.findById(post.getId()).get();
    
    	return "{\"state\":\"ok\"}"+postLazy.getComments();
 
    }
    
    @GetMapping(value=SEARCH)
    public String readState3() {
    	Post postR = repoPost.findById(Long.valueOf(1)).get();
        //	postR.addComment(comment);
        //	repoPost.save(postR);
        return "{\"state\":\"ok\"}"+postR.getComments();
    	
    }
    
    @GetMapping(value=STATE_1)
    public String readState2() {
    

    	Comment2 comment = new Comment2("Comment");
    //	repoComment.save(comment);
    	
    	Comment2 comment1 = new Comment2("Comment");
    //	repoComment.save(comment1);

    	Comment2 comment2 = new Comment2("Comment");

    	

    	Post2 post = new Post2("Title POST");
    	repoPost2.save(post);
    	comment.setPost(post);
    	
    	repoComment2.save(comment);

    	
    	Comment2 commentR = repoComment2.findById(Long.valueOf(1)).get();
    //	postR.addComment(comment);
    //	repoPost.save(postR);
    	
    	return "{\"state\":\"ok\"}"+commentR.getPost();
 
    }


 

}