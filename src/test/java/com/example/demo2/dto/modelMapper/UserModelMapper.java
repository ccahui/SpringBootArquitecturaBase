package com.example.demo2.dto.modelMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;


import com.example.demo2.TestConfig;
import com.example.demo2.dto.CategoryCreationDto;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.dto.UserCreationDto;
import com.example.demo2.dto.UserDto;
import com.example.demo2.models.Category;
import com.example.demo2.models.Comment;
import com.example.demo2.models.Post;
import com.example.demo2.models.User;
import com.example.demo2.repositories.RepositoryComment;
import com.example.demo2.repositories.RepositoryUser;
import com.example.demo2.repositories.EntitiesIT;
import com.example.demo2.repositories.RepositoryCategory;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import javax.transaction.Transactional;

@TestConfig
public class UserModelMapper {

	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private ModelMapper modelMapper;
	
	@Test
	@Transactional
	public void userToUserDto() {
		User user = new User("test@example.com", "123456","Test");
		
		repoUser.save(user);
		
		UserDto dto = modelMapper.map(user, UserDto.class);
		
		assertEquals(user.getId(), dto.getId());
		assertEquals(user.getEmail(), dto.getEmail());
		
	}
	
	@Test
	@Transactional
	public void userCreationDtoToUser() {
		
		UserCreationDto userCreationDto = new UserCreationDto("test@example", "123456", "test");

		User user= modelMapper.map(userCreationDto, User.class);
		
		assertEquals(userCreationDto.getEmail(), user.getEmail());
	}
	
	

}
