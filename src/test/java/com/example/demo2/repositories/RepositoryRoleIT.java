package com.example.demo2.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.EnumRole;
import com.example.demo2.models.Role;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
@TestConfig
public class RepositoryRoleIT {

	@Autowired
	private RepositoryRole repoRole;
	
	@PersistenceContext
	private EntityManager em;
	 
	@Test
	@Transactional
	void testCreateRole() {
		Role nuevo = new Role(EnumRole.ADMIN);
		repoRole.save(nuevo);
		assertEquals(1,  repoRole.count());
	}
	
	@Test
	@Transactional
	void createRoleNotValidtoSQL() {
		em.createNativeQuery("INSERT INTO role (role) VALUES (?)")
	      .setParameter(1, "das")
	      .executeUpdate();
		
		assertEquals(1,  repoRole.count());
	}
	
	
}
