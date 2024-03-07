package com.example.dashboard.validator;

import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.web.representation.request.user.RequestUser;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validatePayload(RequestUser payload){
        if(!validateEmail(payload.email())){
            //exception
        }

        if(!validatePassword(payload.password())){
            throw new ValidationException("The password is invalid.");
        }

    }

    public boolean validateEmail(String email){
        return false;
    }
    public boolean validatePassword(String password){
        return false;
    }

}
