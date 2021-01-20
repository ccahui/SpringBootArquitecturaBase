package com.example.demo2.services;

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
import com.example.demo2.rest_controller.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import javax.transaction.Transactional;
@TestConfig
public class UserServiceIT {
	@Autowired
	private ServiceUser userService;
	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private RepositoryRole repoRole;
	
	@Test
	@Transactional
	public void testCreate() {
		UserCreationDto dto = new UserCreationDto("test@example.com", "123456", "Test Example");
		UserDto user = userService.create(dto);
		assertEquals(1, repoUser.count());
		assertNotNull(user.getId());
	}
	@Test
	@Transactional
	public void testLoginCredentialsValid() {
		
		String email = "test@xample.com";
		String password = "123456";
		createUser(email, password);
		
		UserAuthenticationDto dto = new UserAuthenticationDto(email, password);
		TokenDto token = userService.login(dto);
		
		assertEquals(3, token.getToken().split("\\.").length);
	}
	@Test
	@Transactional
	public void testLoginCredentialsInvalid() {
		UserAuthenticationDto dto = new UserAuthenticationDto("test@example", "123456");
		assertThrows(LoginException.class, () -> userService.login(dto));
	}
	@Test
	@Transactional
	void testListAll() {
		
		createUser("test@example.com","123456");
		List<UserDto> usersDto = userService.listAll();
		assertEquals(1, repoUser.count());
		assertNotNull(usersDto.get(0).getId());
		
	}
	@Test
	@Transactional
	void testDeleteUser() {
		User user = createUser();
		userService.delete(user.getId());
		assertEquals(0, repoUser.count());
	}
	
	@Test
	@Transactional
	void testReadUser() {
		User user = createUser();
		UserDto search = userService.read(user.getId());
		assertEquals(user.getId(), search.getId());
	}
	@Test
	@Transactional
	void testReadUserNotFound() {
		Long idInvalid = Long.valueOf(1);
		assertThrows(NotFoundException.class, () -> userService.read(idInvalid));
	}
	
	
	private User createUser() {
		return this.createUser("test@example.com", "123456");
	}
	
	private User createUser(String email, String password) {
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
