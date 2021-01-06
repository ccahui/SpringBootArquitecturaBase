package com.example.demo2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;

public interface RepositoryPost extends JpaRepository<Post, Long>{

	@Query("select comment from Post post join post.comments comment where post.id = ?1")
	List<Comment> findPostWithComments(Long id);
	
}
