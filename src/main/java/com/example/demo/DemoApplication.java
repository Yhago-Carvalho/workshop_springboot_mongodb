package com.example.demo;

import com.example.demo.domain.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	private final UserRepository userRepository;

	public DemoApplication(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
