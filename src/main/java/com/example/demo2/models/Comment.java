package com.example.demo2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	
	public Comment() {}
	
	public Comment(String comment) {
		this.content = comment;
	}
	public Long getId() {
		return id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		String format = "Comment[id=%d, content='%s'] ";
		return String.format(format, id, content);
	}
	
}
