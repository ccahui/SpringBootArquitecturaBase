package com.example.demo2.rest_controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;


import com.example.demo2.ApiTestConfig;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryPost;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;


@ApiTestConfig
public class ResourcePostIT {
	
	@Autowired
	private RepositoryComment repoComment;
	@Autowired
	private RepositoryPost repoPost;
	
	@Autowired
	private WebTestClient webTestClient;
	
	@BeforeEach
	void seedDb() {
		for(int i = 0; i < 2 ; i++) {
			createPostWith3Comments();
		}
	}
	@Test
	void testListAll() {		
		List<Post> posts = this.webTestClient.get().uri(PostResource.POSTS)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Post.class)
				.returnResult().getResponseBody();
		
		assertEquals(2, posts.size());
	}
	
	@Test
	void testSave() {
		Post post = new Post("Nuevo Post");
		Post response = this.webTestClient.post().uri(PostResource.POSTS)
				.body(BodyInserters.fromValue(post))
				.exchange()
				.expectStatus().isOk()
				.expectBody(Post.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getTitle(), post.getTitle());
	}
	

	@Test
	void testUpdatePostPresent() {
		Long id = createPostWith3Comments();
		Post updatePost = new Post("Actualizar Titulo Nuevo");
		
		Post response = this.webTestClient.put().uri(PostResource.POSTS+PostResource.ID, id)
				.body(BodyInserters.fromValue(updatePost))
				.exchange()
				.expectStatus().isOk()
				.expectBody(Post.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getTitle(), updatePost.getTitle());
	}

	@Test
	void testUpdatePostNotPresent() {
		int id = 666;
		Post updatePost = new Post("Actualizar Titulo Nuevo");
		
		this.webTestClient.put().uri(PostResource.POSTS+PostResource.ID, id)
				.body(BodyInserters.fromValue(updatePost))
				.exchange()
				.expectStatus().isNotFound();
				
	}
	
	@Test
	void testReadPostPresent() {
		Post post = new Post("Nuevo Post");
		post = repoPost.save(post);
		
		Post response = this.webTestClient.get().uri(PostResource.POSTS+PostResource.ID, post.getId())
				.exchange()
				.expectStatus().isOk()
				.expectBody(Post.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getId(), post.getId());
	}
	@Test
	void testReadPostNotPresent() {
		int id = 555;
		this.webTestClient.get().uri(PostResource.POSTS+PostResource.ID, id)
				.exchange()
				.expectStatus().isNotFound();
	}
	
	@Test
	void testDeletePostPresent() {
		Post post = new Post("Nuevo Post");
		post = repoPost.save(post);
		
		this.webTestClient.delete().uri(PostResource.POSTS+PostResource.ID, post.getId())
				.exchange()
				.expectStatus().isOk();
	}
	
	@Test
	void testDeletePostNotPresent() {
		int id = 555;
		this.webTestClient.delete().uri(PostResource.POSTS+PostResource.ID, id)
				.exchange()
				.expectStatus().isOk();
	}
	
	
	@Test
	void testFetchEager() {
	    	Post postR = repoPost.findById(createPostWith3Comments()).get();
	    	System.out.println(postR.getComments());
	    	
	    	int comments = postR.getComments().size();
	    	assertEquals(3, comments );
	}
	@Test
	void testFetchLazy() {
	    	Post postR = repoPost.findById(createPostWith3Comments()).get();
	    
	    	int comments = repoPost.findPostWithComments(postR.getId()).size();
	    	assertEquals(3, comments );
	}
	
	Long createPostWith3Comments() {

		Post post = new Post("Title POST");
		
		Comment comment = new Comment("Comment 01");
    	Comment comment1 = new Comment("Comment 02");
    	Comment comment2 = new Comment("Comment 03");

    	post.addComment(comment);    	
    	post.addComment(comment1);   
    	post.addComment(comment2);   
   
    	repoPost.save(post);
    	return post.getId();
	}

	@AfterEach
	void drowp() {
		repoPost.deleteAll();
	}
	
	
}
