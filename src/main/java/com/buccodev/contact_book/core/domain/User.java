package com.buccodev.contact_book.core.domain;

import com.buccodev.contact_book.core.exceptions.EmailValidationException;
import com.buccodev.contact_book.core.exceptions.PasswordValidationException;

import java.util.*;
import java.util.regex.Pattern;

public class User {

    private Long id;
    private String name;
    private String email;
    private String password;
    private final Set<Contact> contacts = new HashSet<>();


    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.setEmail(email);
        this.setPassword(password);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new EmailValidationException("Email cannot be empty or null!");
        }

        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (!email.matches(emailRegex)) {
            throw new EmailValidationException("Invalid email format!");
        }

        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new PasswordValidationException("Password must have at least 6 characters!");
        }
        this.password = password;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
