package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.EntitiesIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@TestConfig
public class EntitiesIT {
	
	 @PersistenceContext
	 private EntityManager em;
	 @Autowired
	 private RepositoryPost repoPost;
	 

	 @BeforeEach
		void seedDb() {
			for(int i = 0; i < 2 ; i++) {
				createPostWith3Comments();
			}
		}
	@Test
	@Transactional
	void testPostWithComments() {
		List<Post> posts = em.createQuery("select m from Post m", Post.class).getResultList();
		System.out.println(posts);
		assertEquals(2, posts.size());
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

	
	
}
