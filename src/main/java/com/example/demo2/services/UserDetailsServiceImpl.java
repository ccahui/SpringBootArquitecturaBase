package com.example.demo2.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo2.models.Role;
import com.example.demo2.models.User;
import com.example.demo2.repositories.RepositoryUser;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Transactional
@Qualifier("example.users")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private RepositoryUser repoUser;

    @Override
    public UserDetails loadUserByUsername(final String username) {
    	User user = repoUser.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException("Usuario not found. "+ username));
    	return userBuilder(user);
    }

    private org.springframework.security.core.userdetails.User userBuilder(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : user.getRoles()) {
        	String authorityDescription = role.getRole().toString();
            authorities.add(new SimpleGrantedAuthority(authorityDescription));
        }
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), true, true, true,
                true, authorities);
    }
}
