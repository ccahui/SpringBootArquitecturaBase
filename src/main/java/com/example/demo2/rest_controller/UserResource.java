package com.example.demo2.rest_controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.dto.TokenDto;
import com.example.demo2.dto.UserAuthenticationDto;
import com.example.demo2.dto.UserCreationDto;
import com.example.demo2.dto.UserDto;
import com.example.demo2.services.ServiceUser;

@RestController
public class UserResource {
	
	@Autowired
	private ServiceUser userService;
	public static final String LOGIN = "/logina";
	public static final String USERS = "/users";
	public static final String ID = "/{id}";
	
	@PostMapping(value = LOGIN)
	public TokenDto token(@Valid @RequestBody UserAuthenticationDto body){
		//return userService.login(body);
		return userService.login(body);
	}
	@PostMapping(value = USERS)
	public UserDto create (@Valid @RequestBody UserCreationDto body){
		return userService.create(body);
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = USERS)
	public List<UserDto> listAll(Authentication authentication) {
		return userService.listAll();
	}
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping(value = USERS+ID)
	public UserDto read(@PathVariable Long id){
		return userService.read(id);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = USERS+ID)
	public void delete(@PathVariable Long id){
		userService.delete(id);
	}
	
	
}
