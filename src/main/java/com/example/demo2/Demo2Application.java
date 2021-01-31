package com.example.demo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo2.models.EnumRole;
import com.example.demo2.models.Post;
import com.example.demo2.models.Role;
import com.example.demo2.models.User;
import com.example.demo2.repositories.RepositoryPost;
import com.example.demo2.repositories.RepositoryRole;
import com.example.demo2.repositories.RepositoryUser;

@SpringBootApplication
public class Demo2Application implements CommandLineRunner {

	@Autowired
	RepositoryRole repoRole;
	@Autowired
	RepositoryUser repoUser;
	@Autowired
	RepositoryPost repoPost;
	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(Demo2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//createUserWith2Roles();
		createPosts(100);
	}
	
	private void createPosts(int post) {
		for(int i = 0; i < post; i++) {
			repoPost.save(new Post("Post Título "+i));
		}
		
	}

	private User createUserWith2Roles() {
		Role roleAdmin = new Role(EnumRole.ADMIN);
		Role roleUser = new Role(EnumRole.USER);
		repoRole.save(roleAdmin);
		repoRole.save(roleUser);

		String passwordEncode = encoder.encode("123456");
		User user = new User("Admin", "admin@example.com", passwordEncode);
		repoUser.save(user);
		user.getRoles().add(roleAdmin);
		user.getRoles().add(roleUser);
		repoUser.save(user);
		return user;
	}

}
