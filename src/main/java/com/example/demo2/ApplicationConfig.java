package com.example.demo2;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo2.dto.modelMapper.CategoryCreationDtoToCategory;
import com.example.demo2.dto.modelMapper.CategoryToCategoryDto;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new CategoryToCategoryDto());
        modelMapper.addMappings(new CategoryCreationDtoToCategory());
        
        return modelMapper;
    }
}
