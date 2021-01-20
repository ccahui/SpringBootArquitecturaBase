package com.example.demo2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo2.DataBaseCleanup;
import com.example.demo2.TestConfig;
import com.example.demo2.dto.CategoryDto;
import com.example.demo2.dto.TokenDto;
import com.example.demo2.dto.UserAuthenticationDto;
import com.example.demo2.dto.UserCreationDto;
import com.example.demo2.dto.UserDto;
import com.example.demo2.models.EnumRole;
import com.example.demo2.models.Role;
import com.example.demo2.models.User;
import com.example.demo2.repositories.RepositoryRole;
import com.example.demo2.repositories.RepositoryUser;
import com.example.demo2.rest_controller.exception.LoginException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;
@TestConfig
public class DataBaseCleanupIT {
	
	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RepositoryRole repoRole;
	@Autowired
	private DataBaseCleanup dbCleanUp;
	
	
	
	
	@Test
	void testTruncateAllTables() {
		createUserWith2Roles("test@example.com", "123456");
		assertEquals(1, repoUser.count());
		assertEquals(2, repoRole.count());
		dbCleanUp.truncateAllTables();
		assertEquals(0, repoUser.count());
		assertEquals(0, repoRole.count());
	}
	
	private User createUserWith2Roles(String email, String password) {
		Role roleAdmin = new Role(EnumRole.ADMIN);
		Role roleUser = new Role(EnumRole.USER);
		repoRole.save(roleAdmin);
		repoRole.save(roleUser);
		
		String passwordEncode = encoder.encode(password);
		User user = new User("Test", email, passwordEncode);
		user.getRoles().add(roleAdmin);
		user.getRoles().add(roleUser);
		repoUser.save(user);
		return user;
	}
	
}
