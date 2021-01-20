package com.example.demo2.dto;

import javax.validation.constraints.NotBlank;

public class UserCreationDto extends UserAuthenticationDto {
	@NotBlank
	private String name;
	
	public UserCreationDto () {}
	public UserCreationDto(String username, String password, String name) {
		super(username, password);
		this.name = name;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
