package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.CategoryItem;
import com.example.demo2.models.IdCategoryItem;

@Repository
public interface RepositoryCategoryItem extends JpaRepository<CategoryItem, IdCategoryItem>{

}
