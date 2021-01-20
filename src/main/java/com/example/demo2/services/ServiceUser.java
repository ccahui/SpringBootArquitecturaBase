package com.example.demo2.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo2.dto.CategoryDto;
import com.example.demo2.dto.TokenDto;
import com.example.demo2.dto.UserAuthenticationDto;
import com.example.demo2.dto.UserCreationDto;
import com.example.demo2.dto.UserDto;
import com.example.demo2.models.Category;
import com.example.demo2.models.Role;
import com.example.demo2.models.User;
import com.example.demo2.repositories.RepositoryUser;
import com.example.demo2.rest_controller.exception.LoginException;
import com.example.demo2.rest_controller.exception.NotFoundException;

@Service
public class ServiceUser {
	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private ModelMapper modelMapper;

	
	public TokenDto login(UserAuthenticationDto login) {
		User user = repoUser.findByEmail(login.getEmail()).orElseThrow(() -> new LoginException("User or Password Incorrecto"));
		if (verifyPassword(login.getPassword(), user.getPassword())) {
			return this.generateToken(user);
		} else {
			throw new LoginException("User or Password Incorrecto");
		}
	}

	public UserDto create(UserCreationDto body) {
		User user = userCreationDtoToUser(body);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repoUser.save(user);
		UserDto dto = userToUserDto(user);
		return dto;
	}
	
	public List<UserDto> listAll() {
		List<User> users = repoUser.findAll();
		List<UserDto> usersDto = users.stream().map(user  -> userToUserDto(user))
		          .collect(Collectors.toList());
		return usersDto;
	}
	
	public void delete(Long id) {
		Optional<User> user = repoUser.findById(id);
		if (user.isPresent()) {
			repoUser.deleteById(id);
		}
	}
	
	public UserDto read(Long id) {
		User user = repoUser.findById(id).orElseThrow(()-> new NotFoundException("User id ("+ id +")"));
		UserDto dto = userToUserDto(user);
		return dto;
	}
	
	

	private User userCreationDtoToUser(UserCreationDto body) {
		User user = modelMapper.map(body, User.class);
		return user;
	}

	private UserDto userToUserDto(User user) {
		UserDto dto = modelMapper.map(user, UserDto.class);
		return dto;
	}



	private boolean verifyPassword(String password_plan_text, String encoded_password) {
		return passwordEncoder.matches(password_plan_text, encoded_password);
	}

	private TokenDto generateToken(User user) {
		
		String[] roles = getRoles(user);
		String token = jwtService.createToken(user.getEmail(), roles);
		return new TokenDto(token);
	}
	private String[] getRoles(User user) {
		String[] roles = user.getRoles().stream().map(rol -> rol.getRole().toString()).toArray(String[]::new);
		return roles;
	}
}
