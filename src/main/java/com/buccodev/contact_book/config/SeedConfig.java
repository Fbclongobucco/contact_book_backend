package com.buccodev.contact_book.config;

import com.buccodev.contact_book.dto.ContactDTO;
import com.buccodev.contact_book.dto.UserDTO;
import com.buccodev.contact_book.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("dev")
@Configuration
public class SeedConfig implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {

        var userDto = new UserDTO("Roberto Santos", "123", "robith@gmail.com",
                List.of(new ContactDTO(null, "Lota", "21973333339")));

        userService.createAdminUser(userDto);

    }
}
