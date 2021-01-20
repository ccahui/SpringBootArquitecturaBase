package com.example.demo2.services;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo2.TestConfig;
import com.example.demo2.models.EnumRole;
import com.example.demo2.rest_controller.exception.JwtException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestConfig
class JwtServiceIT {

	@Autowired
	private JwtService jwtService;

	private String jwtToken;
	private String[] roles;

	@BeforeEach
	void createToken() {
		roles = new String[] { EnumRole.ADMIN.toString(), EnumRole.USER.toString() };
		this.jwtToken = this.jwtService.createToken("test@example.com", roles);
	}

	@Test
	void testCreateToken() {
		assertNotNull(this.jwtToken);
		assertEquals(3, this.jwtToken.split("\\.").length);
		LogManager.getLogger(this.getClass()).info("===>>> jwt---" + this.jwtToken + "---");
	}

	@Test
	void testIsBearer() {
		assertTrue(this.jwtService.isBearer(JwtService.BEARER + this.jwtToken));
	}

	@Test
	void testUser() {
		assertEquals("test@example.com", this.jwtService.user(JwtService.BEARER + this.jwtToken));
	}

	@Test
	void testUserExceptionNonBearer() {
		assertThrows(JwtException.class, () -> this.jwtService.user("kk " + this.jwtToken));
	}

	@Test
	void testUserExceptionNonJwt() {
		String jwtInvalid = this.jwtToken + "s";
		assertThrows(JwtException.class, () -> this.jwtService.user(JwtService.BEARER + jwtInvalid));
	}

	@Test
	void testUserExceptionExpiredJwt() {
		String jwt = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9"
				+ ".eyJuYmYiOjE2MTA3MTM2NDEsInJvbGVzIjpbIkFETUlOIiwiVVNFUiJdLCJpc3MiOiJkZW1vMi1leGFtcGxlIiwiZXhwIjoxNjEwNzE3MjQxLCJpYXQiOjE2MTA3MTM2NDEsInVzZXIiOiJ0ZXN0QGV4YW1wbGUuY29tIn0"
				+ ".fbqNLHZW2pIejWLoJFMHXF9RJYiUP9wOt-_EmbNu6iU";

		assertThrows(JwtException.class, () -> this.jwtService.user(JwtService.BEARER + jwt));
	}

	@Test
	void testRoles() {
		List<String> roleList = this.jwtService.roles(JwtService.BEARER + this.jwtToken);
		assertArrayEquals(roles, roleList.toArray());
	}
}
