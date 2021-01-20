package com.example.demo2.models;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Column(nullable = false)
	private String password;
	@ManyToMany
	private Set<Role> roles = new HashSet<>();
	
	public User() {}
	
	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public int hashCode() {
		return id.hashCode()+email.hashCode();
	}

	public boolean equals(Object o) {
		if (o != null && o instanceof User) {
			User that = (User) o;
			return id.equals(that.getId()) && email.equals(that.getEmail()) && name.equals(that.getName());
		}
		return false;
	}

	
	@Override
	public String toString() {
		String format = "User[id=%d, name='%s', email='%s', password='%s'] ";
		return String.format(format, id, name, email, password);
	}
	
	

}
