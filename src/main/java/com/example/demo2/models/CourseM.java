package com.example.demo2.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CourseM {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "course")
	private Set<CourseRegistration> courseRegistration = new HashSet<>();

	public CourseM() {
	}

	public CourseM(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CourseRegistration> getCourseRegistration() {
		return courseRegistration;
	}

	public void setCourseMaterial(Set<CourseRegistration> courseRegistration) {
		this.courseRegistration = courseRegistration;
	}

	
	public String toString() {
		String format = "Course[id=%d, name='%s', courseMaterial='%s] ";
		return String.format(format, id, name, courseRegistration);
	}

}
