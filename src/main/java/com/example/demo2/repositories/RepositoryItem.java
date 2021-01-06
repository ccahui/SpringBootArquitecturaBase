package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.Item;
@Repository
interface RepositoryItem extends JpaRepository<Item, Long>{

}
