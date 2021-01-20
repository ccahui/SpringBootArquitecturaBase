package com.example.demo2.dto.modelMapper;

import org.modelmapper.PropertyMap;

import com.example.demo2.dto.UserDto;
import com.example.demo2.models.User;

public class UserToUserDto extends PropertyMap<User, UserDto>{

	@Override
	protected void configure() {
		map().setName(source.getName());
		 map().setEmail(source.getEmail());
		 map().setId(source.getId());
	}


}
