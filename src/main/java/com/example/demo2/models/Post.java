package com.example.demo2.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	private List<Comment> comments;
	
	
	public Post() {}
	public Post(String title) {
		this.title = title;
	}
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void addComment(Comment comment) {
		if(comments == null) {
			comments = new ArrayList();
		}
		comments.add(comment);
		//this.coments.add(comment);
	}
	@Override
	public String toString() {
		String format = "Post[id=%d, title='%s'] ";
		return String.format(format, id, title)+comments;
	}
}
