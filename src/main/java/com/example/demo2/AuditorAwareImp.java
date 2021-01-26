package com.example.demo2;

import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuditorAwareImp implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String username = usernameCurrent();
		return Optional.of(username);
		// Use below commented code when will use Spring Security.
	}

	private String usernameCurrent() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authentication);
		if (isAuthenticadeUser(authentication)) {
			return "anonimous";
		}
		return authentication.getName();
	}
	private boolean isAuthenticadeUser(Authentication authentication){
		return authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken;
	}
}