package com.example.demo2;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo2.dto.modelMapper.CategoryCreationDtoToCategory;
import com.example.demo2.dto.modelMapper.CategoryToCategoryDto;
import com.example.demo2.dto.modelMapper.UserCreationDtoToUser;
import com.example.demo2.dto.modelMapper.UserToUserDto;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //Categories
        modelMapper.addMappings(new CategoryToCategoryDto());
        modelMapper.addMappings(new CategoryCreationDtoToCategory());
        //User
        modelMapper.addMappings(new UserToUserDto());
        modelMapper.addMappings(new UserCreationDtoToUser());
        
        return modelMapper;
    }
}
