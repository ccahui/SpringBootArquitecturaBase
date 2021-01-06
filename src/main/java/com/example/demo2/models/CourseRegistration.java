package com.example.demo2.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CourseRegistration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Student student;	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private CourseM course;
	private Date registeredAt;

	public CourseRegistration() {
	}
	
	public CourseRegistration(Student student, CourseM course) {
		this.course = course;
		this.student = student;
		//Sincronized
		this.course.getCourseRegistration().add(this);
		this.student.getCourseRegistration().add(this);
	}

	public Long getId() {
		return id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseM getCourse() {
		return course;
	}

	public void setCourse(CourseM course) {
		this.course = course;
	}

	public Date getRegisteredAt() {
		return registeredAt;
	}

	public void setRegisteredAt(Date registeredAt) {
		this.registeredAt = registeredAt;
	}
	public String toString() {
		String format = "CourseRegistration[course=%s, student='%s' ] ";
		return String.format(format, course, student);
	}
	
	
	
	
}
