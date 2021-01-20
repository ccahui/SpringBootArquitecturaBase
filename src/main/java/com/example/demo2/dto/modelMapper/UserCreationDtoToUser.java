package com.example.demo2.dto.modelMapper;

import org.modelmapper.PropertyMap;

import com.example.demo2.dto.UserCreationDto;
import com.example.demo2.models.User;

public class UserCreationDtoToUser extends PropertyMap<UserCreationDto, User>{
	@Override
	protected void configure() {
		  map().setName(source.getName());
		  map().setEmail(source.getEmail());
		  map().setPassword(source.getPassword());
	}

}
