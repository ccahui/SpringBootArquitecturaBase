package com.example.demo2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo2.models.User;
import java.util.Optional;
@Repository
public interface RepositoryUser extends JpaRepository<User, Long>{

	public Optional<User> findByEmail(String email);
}
