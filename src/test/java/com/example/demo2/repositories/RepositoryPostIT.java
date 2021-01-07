package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryPostIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;

@TestConfig
public class RepositoryPostIT {
	
	@Autowired
	private RepositoryComment repoComment;
	@Autowired
	private RepositoryPost repoPost;


	@Test
	@Transactional
	void testPostWithComments() {
	    	
			Long id = createPost();
			List<Comment> postComments = repoPost.findPostWithComments(id);
	    	
	    	assertEquals(2, postComments.size());
	}
	
	Long createPost() {

		Post post = new Post("Title POST");
		
		Comment comment = new Comment("Comment 01");
    	Comment comment1 = new Comment("Comment 02");

    	post.addComment(comment);    	
    	post.addComment(comment1);   
    	

    	repoPost.save(post);
    	
    	Comment comment2 = new Comment("Comment 03");
    	repoComment.save(comment2);
    	
    	return post.getId();
	}

	
	
	
}
