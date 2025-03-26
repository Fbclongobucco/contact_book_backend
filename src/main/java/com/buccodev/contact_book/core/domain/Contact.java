package com.buccodev.contact_book.core.domain;

import com.buccodev.contact_book.core.exceptions.PasswordValidationException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contact {

    private Long id;
    private String name;
    private String number;
    private String contactPhoto;
    private User user;


    private final String PHONE_REGEX = "^\\+?[0-9]{10,15}$";
    private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public Contact(Long id, String name, String numberNumber,  User user) {
        this.id = id;
        this.name = name;
        this.setNumber(numberNumber);
        this.contactPhoto = "https://placehold.co/400";
        this.user = user;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (number == null || !PHONE_PATTERN.matcher(number).matches()) {
            throw new PasswordValidationException("Invalid phone number!");
        }
        this.number = number;
    }

    public String getContactPhoto() {
        return contactPhoto;
    }

    public void setContactPhoto(String contactPhoto) {
        this.contactPhoto = contactPhoto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(number, contact.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, number);
    }

}
