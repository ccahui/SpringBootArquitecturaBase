package com.example.demo2.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class CourseMaterial {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String urlMaterial;
	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(nullable = false)
	private Course course;
	
	
	public CourseMaterial() {}
	public CourseMaterial(String urlMaterial) {
		this.urlMaterial = urlMaterial;
	}
	
	public Long getId() {
		return id;
	}
	public String getUrlMaterial() {
		return urlMaterial;
	}
	public void setUrlMaterial(String urlMaterial) {
		this.urlMaterial = urlMaterial;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	
	
	
}
