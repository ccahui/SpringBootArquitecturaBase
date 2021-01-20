package com.example.demo2.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserAuthenticationDto {

	@NotBlank
	@Email
	private String email;
	@NotBlank
	@Size(min = 4, max = 16)
	private String password;
	
	public UserAuthenticationDto() {}
	
	public UserAuthenticationDto(String username, String password) {
		this.email = username;
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String username) {
		this.email = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
