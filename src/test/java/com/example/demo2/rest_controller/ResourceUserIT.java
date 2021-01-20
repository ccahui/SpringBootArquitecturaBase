package com.example.demo2.rest_controller;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;

import javax.transaction.Transactional;

import com.example.demo2.ApiTestConfig;
import com.example.demo2.DataBaseCleanup;
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
import com.example.demo2.services.JwtService;

@ApiTestConfig
public class ResourceUserIT {

	@Autowired
	private DataBaseCleanup dbclean;
	@Autowired
	private WebTestClient webTestClient;
	@Autowired
	private RepositoryUser repoUser;
	@Autowired
	private RepositoryRole repoRole;
	@Autowired
	private PasswordEncoder encoder;
	private String jwt;
	private User user;

	@BeforeEach
	void createToken() {
		user = createUser();
		String email = "test@example.com";
		String password = "123456";
		UserAuthenticationDto dto = new UserAuthenticationDto(email, password);
		TokenDto token = webTestClient.post().uri(UserResource.LOGIN).body(BodyInserters.fromValue(dto)).exchange()
				.expectStatus().isOk().expectBody(TokenDto.class).returnResult().getResponseBody();
		this.jwt = token.getToken();
	}
	
	@Test
	void login() {
		String email = "test@example.com";
		String password = "123456";
		UserAuthenticationDto dto = new UserAuthenticationDto(email, password);
		TokenDto token = webTestClient.post().uri(UserResource.LOGIN).body(BodyInserters.fromValue(dto)).exchange()
				.expectStatus().isOk().expectBody(TokenDto.class).returnResult().getResponseBody();
		this.jwt = token.getToken();
	}
	@Test
	void testCreateUserSuccess() {
		UserCreationDto dto = new UserCreationDto("kccahui@unsa.edu.pe", "123456", "Kristian");
		
		UserDto response = this.webTestClient.post().uri(UserResource.USERS)
				.body(BodyInserters.fromValue(dto))
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserDto.class)
				.returnResult().getResponseBody();	
		assertEquals(response.getEmail(), dto.getEmail());
	}

	@Test
	void testListAll() {
		
		 this.webTestClient
         .get().uri(UserResource.USERS)
         .header("Authorization", JwtService.BEARER + this.jwt)
         .exchange()
         .expectStatus().isOk();
	}
	
	@Test
	void testReadUserPresent() {
		Long id = user.getId();
		UserDto response = this.webTestClient.get().uri(UserResource.USERS+UserResource.ID, id)
				.header("Authorization", JwtService.BEARER + this.jwt)
				.exchange()
				.expectStatus().isOk()
				.expectBody(UserDto.class)
				.returnResult().getResponseBody();
		
		assertEquals(response.getId(), id);
	}
	@Test
	void testReadUserNotPresent() {
		int id = 9999;
		this.webTestClient.get().uri(UserResource.USERS+UserResource.ID, id)
		 	.header("Authorization", JwtService.BEARER + this.jwt)
				.exchange()
				.expectStatus().isNotFound();
	}
	

	@Test
	void testDeleteUserPresent() {
		Long id = user.getId();
		this.webTestClient.delete().uri(CategoryResource.CATEGORIES+CategoryResource.ID, id)
		.header("Authorization", JwtService.BEARER + this.jwt)
				.exchange()
				.expectStatus().isOk();
	}
	
	@Test
	void testDeleteUserNotPresent() {
		int id = 9999;
		this.webTestClient.delete().uri(PostResource.POSTS+PostResource.ID, id)
		.header("Authorization", JwtService.BEARER + this.jwt)
				.exchange()
				.expectStatus().isOk();
	}
	
	private User createUser() {
		Role roleAdmin = new Role(EnumRole.ADMIN);
		Role roleUser = new Role(EnumRole.USER);
		repoRole.save(roleAdmin);
		repoRole.save(roleUser);
		
		String passwordEncode = encoder.encode("123456");
		User user = new User("Test", "test@example.com", passwordEncode);
		repoUser.save(user);
		user.getRoles().add(roleAdmin);
		user.getRoles().add(roleUser);
		repoUser.save(user);
		return user;
	}

	@AfterEach
	void drowp() {
		dbclean.truncateAllTables();
	}
}
