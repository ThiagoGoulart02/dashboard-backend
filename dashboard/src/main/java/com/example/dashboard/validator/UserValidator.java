package com.example.dashboard.validator;

import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.web.representation.request.user.RequestUser;
import org.springframework.stereotype.Component;


@Component
public class UserValidator {

    private final int MIN_PASSWORD_SIZE = 7;

    public void validatePayload(RequestUser payload){
        if(!validateEmail(payload.email())){
            throw new ValidationException("The email is invalid.");
        }

        if(!isPasswordValid(payload.password())){
            throw new ValidationException("The password is invalid.");
        }

        if(payload.company_name().isEmpty()) throw new ValidationException("The company name cannot be empty");
    }

    public boolean validateEmail(String email){
        return email.contains("@") && email.contains("gmail.com");
    }
    public boolean isPasswordValid(String password){
        return password.length()>=MIN_PASSWORD_SIZE &&
                password.chars().anyMatch(Character::isLetter) &&
                password.chars().anyMatch(Character::isDigit) &&
                password.chars().anyMatch(Character::isUpperCase) &&
                password.chars().anyMatch(Character::isLowerCase);
    }

}
