package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.Course;

@Repository
public interface RepositoryCourse extends JpaRepository<Course, Long>{

}
