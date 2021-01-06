package com.example.demo2.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;

@Repository
public interface RepositoryComment extends JpaRepository<Comment, Long>{

	@Query("select post from Post post join post.comments comment where comment.id = ?1")
	List<Post> findPost(Long id);
}
