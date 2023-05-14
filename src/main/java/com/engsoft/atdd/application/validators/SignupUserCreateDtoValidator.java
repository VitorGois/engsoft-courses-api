package com.engsoft.atdd.application.validators;

import com.engsoft.atdd.infraestructure.exceptions.BadRequestException;

public class SignupUserCreateDtoValidator {
    public static void validateEmail(String email) throws BadRequestException {
        if (!EmailValidator.validate(email)) {
            throw new BadRequestException("invalid email");
        }
    }

    public static void validatePassword(String password) throws BadRequestException {
        if (!PasswordValidator.validate(password)) {
            throw new BadRequestException("invalid password");
        }
    }
}
