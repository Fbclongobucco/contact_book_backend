package com.buccodev.contact_book;

import com.buccodev.contact_book.entities.Users;
import com.buccodev.contact_book.repository.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class ContactBookApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private Faker faker;


	@Test
	void contextLoads() {

	}

}
