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

    private  final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private  final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);



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

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new EmailValidationException("invalid email!");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        if(password == null || password.length() < 6){
            throw new PasswordValidationException("Password cannot be empty or have less than 6 digits!");
        }
        this.password = password;
    }

    public Boolean verifyPassword(String password) {
        return this.password.equals(password);
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    public void addContacts(Contact contact) {
        this.contacts.add(contact);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
