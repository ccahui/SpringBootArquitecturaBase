package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Course;
import com.example.demo2.models.CourseMaterial;
import com.example.demo2.models.Post;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryCourseIT;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.hibernate.Hibernate;

@TestConfig
public class RepositoryCourseIT {
	
	@Autowired
	private RepositoryCourse repoCourse;
	@Autowired
	private RepositoryCourseMaterial repoCourseMaterial;

	
	@Test
	@Transactional
	void testCreateCourseWithCourseMaterial() {
		
		Course course = new Course("IT");
		CourseMaterial courseMaterial = new CourseMaterial("https://courses/it");
		
		course.setCourseMaterial(courseMaterial);
		courseMaterial.setCourse(course);
		
		repoCourse.save(course);

		assertNotNull(course.getCourseMaterial());
		assertNotNull(courseMaterial.getCourse());
		assertEquals(1, repoCourse.count());
		assertEquals(1, repoCourseMaterial.count());
		
	}
	
	@Test
	@Transactional
	void testDeleteCourseCascade() {
		Long idCourse = createCourseWithCourseMaterial();
		Course course = repoCourse.findById(idCourse).get();
		
		repoCourse.delete(course);
		assertEquals(0, repoCourse.count());
		assertEquals(0, repoCourseMaterial.count());
		System.out.println(course);
	}
	
	@Test
	void testDeleteCourseMaterialOrphanRemoval() {
		Long idCourse = createCourseWithCourseMaterial();
		Course course = repoCourse.findById(idCourse).get();
		CourseMaterial courseMaterial = course.getCourseMaterial();
		
		courseMaterial.setCourse(null);
		course.setCourseMaterial(null);
		
		repoCourse.save(course);
		assertEquals(0, repoCourseMaterial.count());
		
	}
	
	Long createCourseWithCourseMaterial() {
		Course course = new Course("IT");
		CourseMaterial courseMaterial = new CourseMaterial("https://courses/it");
		
		course.setCourseMaterial(courseMaterial);
		courseMaterial.setCourse(course);
		
		repoCourse.save(course);
		
		return course.getId();
	}
	
	


	
	
	
}
