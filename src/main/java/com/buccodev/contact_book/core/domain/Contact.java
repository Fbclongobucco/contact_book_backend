package com.buccodev.contact_book.core.domain;

import com.buccodev.contact_book.core.exceptions.PasswordValidationException;

import java.util.Objects;
import java.util.regex.Pattern;

public class Contact {

    private Long id;
    private String name;
    private String numberNumber;


    private final String PHONE_REGEX = "^\\+?[0-9]{10,15}$";
    private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);

    public Contact(Long id, String name, String numberNumber) {
        this.id = id;
        this.name = name;
        this.numberNumber = numberNumber;
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

    public String getNumberNumber() {
        return numberNumber;
    }

    public void setNumberNumber(String numberNumber) {
        if (numberNumber == null || !PHONE_PATTERN.matcher(numberNumber).matches()) {
            throw new PasswordValidationException("Invalid phone number!");
        }

        this.numberNumber = numberNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(numberNumber, contact.numberNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberNumber);
    }
}
