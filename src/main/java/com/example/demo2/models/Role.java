package com.example.demo2.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
    private EnumRole role;
	
	public Role() {}
	public Role(EnumRole role) {
		this.role = role;
	}
	
	public Long getId() {
		return id;
	}
	
	public EnumRole getRole() {
		return role;
	}
	public void setRole(EnumRole role) {
		this.role = role;
	}
	
	public boolean equals(Object o) {
		if (o != null && o instanceof User) {
			Role that = (Role) o;
			return id.equals(that.getId()) && role.equals(that.getRole());
		}
		return false;
	}

	
	@Override
	public String toString() {
		String format = "Role[id=%d', role='%s'";
		return String.format(format, id, role);
	}
	
	
}
