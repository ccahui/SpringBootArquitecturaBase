package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.Category;

@Repository
public interface RepositoryCategory extends JpaRepository<Category, Long>{

}
