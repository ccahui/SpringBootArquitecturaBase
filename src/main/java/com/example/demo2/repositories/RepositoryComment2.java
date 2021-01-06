package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.Comment;
import com.example.demo2.models.Comment2;

@Repository
public interface RepositoryComment2 extends JpaRepository<Comment2, Long>{

}
