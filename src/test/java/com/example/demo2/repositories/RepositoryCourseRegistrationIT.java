package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.Bid;
import com.example.demo2.models.Category;
import com.example.demo2.models.CategoryItem;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Course;
import com.example.demo2.models.CourseM;
import com.example.demo2.models.CourseMaterial;
import com.example.demo2.models.CourseRegistration;
import com.example.demo2.models.IdCategoryItem;
import com.example.demo2.models.Item;
import com.example.demo2.models.ItemM;
import com.example.demo2.models.Post;
import com.example.demo2.models.Student;
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
public class RepositoryCourseRegistrationIT {
	
	@Autowired
	private RepositoryCourseM repoCourse;
	@Autowired
	private RepositoryStudent repoStudent;
	@Autowired RepositoryCourseRegistration repoCourseRegistration;

	
	@Test
	@Transactional
	void testCreateCourseRegistrationCascadeTypePersistent() {
		
		CourseM course = new CourseM("FP1");
		Student student = new Student("Test Example");
		
		CourseRegistration courseRegistration = new CourseRegistration(student, course);
		repoCourseRegistration.save	(courseRegistration);

		assertEquals(1, course.getCourseRegistration().size());
		assertEquals(1, student.getCourseRegistration().size());
		assertEquals(1, repoCourse.count());
		assertEquals(1, repoStudent.count());
		assertEquals(1, repoCourseRegistration.count());
		
	}
	
	@Test
	@Transactional
	void testDeleteCategoryItem() {
		Long id = createCourseRegistration();
		CourseRegistration courseRegistration = repoCourseRegistration.findById(id).get();
		
		CourseM course = courseRegistration.getCourse();
		Student student = courseRegistration.getStudent();
		
		course.getCourseRegistration().remove(courseRegistration);
		student.getCourseRegistration().remove(courseRegistration);
	
		repoCourseRegistration.delete(courseRegistration);
		
		
		course = repoCourse.findById(course.getId()).get();
		student = repoStudent.findById(student.getId()).get();
		
		assertEquals(0, repoCourseRegistration.count());
		assertEquals(0,course.getCourseRegistration().size());
		assertEquals(0,student.getCourseRegistration().size());
		
	}
	
	Long createCourseRegistration() {
		CourseM course = new CourseM("FP1");
		Student student = new Student("Test Example");
		
		CourseRegistration courseRegistration = new CourseRegistration(student, course);
		repoCourseRegistration.save	(courseRegistration);
		
		return courseRegistration.getId();
	}
	
	
	
	
	
}
