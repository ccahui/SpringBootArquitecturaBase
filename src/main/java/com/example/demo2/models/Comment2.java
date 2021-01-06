package com.example.demo2.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Comment2 {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String comment;
	@ManyToOne
	private Post2 post;
	
	public Comment2() {}
	
	public Comment2(String comment) {
		this.comment = comment;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}

	public Post2 getPost() {
		return post;
	}

	public void setPost(Post2 post) {
		this.post = post;
	}
	@Override
	public String toString() {
		String format = "Comment2[id=%d, comment='%s'] ";
		return String.format(format, id, comment);
	}

	
	
}
