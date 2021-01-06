package com.example.demo2.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.apache.tomcat.jni.Address;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToOne(mappedBy = "course", fetch = FetchType.LAZY, orphanRemoval = true, cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	private CourseMaterial courseMaterial;
	


	public Course() {	}
	public Course(String name) {
		this.name = name;
	}
	
	public Course(String name, CourseMaterial courseMaterial) {
		this.name = name;
		this.courseMaterial = courseMaterial;
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
	public CourseMaterial getCourseMaterial() {
		return courseMaterial;
	}
	public void setCourseMaterial(CourseMaterial courseMaterial) {
		this.courseMaterial = courseMaterial;
	}
	public String toString() {
		String format = "Course[id=%d, name='%s', courseMaterial='%s] ";
		return String.format(format, id, name, courseMaterial);
	}
	
}
