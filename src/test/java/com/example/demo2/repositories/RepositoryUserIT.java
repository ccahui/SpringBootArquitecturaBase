package com.example.demo2.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

import javax.transaction.Transactional;

import com.example.demo2.TestConfig;
import com.example.demo2.models.EnumRole;
import com.example.demo2.models.Role;
import com.example.demo2.models.User;

@TestConfig
public class RepositoryUserIT {

	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private RepositoryRole repoRole;
	private User user;
	@BeforeEach
	void dbSeed() {
		user = new User("Test", "test@example.com", "123456");
		repoUser.save(user);
	}
	@Test
	@Transactional
	void testFindUserByEmail() {
		User userSearch = repoUser.findByEmail(user.getEmail()).get();
		assertEquals(user, userSearch);
	}
	@Test 
	@Transactional
	void testCreateUserWithRoles() {
		Role roleAdmin = new Role(EnumRole.ADMIN);
		Role roleUser = new Role(EnumRole.ADMIN);
		repoRole.save(roleAdmin);
		repoRole.save(roleUser);
		
		user.getRoles().add(roleUser);
		user.getRoles().add(roleAdmin);
		repoUser.save(user);
		
		assertEquals(2, user.getRoles().size());
		
	}
	
	
	
	
	
}
