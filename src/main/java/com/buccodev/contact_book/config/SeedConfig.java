package com.buccodev.contact_book.config;

import com.buccodev.contact_book.entities.Contact;
import com.buccodev.contact_book.entities.Roles;
import com.buccodev.contact_book.entities.Users;
import com.buccodev.contact_book.repository.ContactRepository;
import com.buccodev.contact_book.repository.RolesRepository;
import com.buccodev.contact_book.repository.UserRepository;
import com.buccodev.contact_book.services.ContactService;
import com.buccodev.contact_book.services.UserService;
import com.github.javafaker.Faker;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Locale;
import java.util.Random;
import java.util.Set;

@Configuration
public class SeedConfig implements CommandLineRunner {

    @Autowired
    public ContactRepository contactRepository;

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public RolesRepository rolesRepository;

    @Autowired
    public BCryptPasswordEncoder passwordEncoder;


    @Bean
    public Faker faker(){
        return new Faker(Locale.ENGLISH);
    }


    @Override
    @Transactional
    public void run(String... args) throws Exception {

/*        var roleAdmin = rolesRepository.findByName(Roles.Values.ADMIN.name().toLowerCase());


        var userAdmin = userRepository.findByEmail("longobucco@gmail.com");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("admin ja existe");
                },
                () -> {
                    var user = new Users();
                    user.setName("Fabricio Longo Bucco");
                    user.setEmail("longobucco@gmail.com");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                }
        ); */

    }

}
