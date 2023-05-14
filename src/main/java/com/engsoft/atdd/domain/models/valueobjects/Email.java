package com.engsoft.atdd.domain.models.valueobjects;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Email {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Column(name = "email")
    private String value;

    private Email(String value) {
        this.value = value;
    }
    
    public Email() { }

    public static Email deString(String email) {
    	validaEmail(email);
        return new Email(email);
    }

    public String getValue() {
        return value;
    }

    private static void validaEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("formato de e-mail inválido");
        }
    }
	
}

