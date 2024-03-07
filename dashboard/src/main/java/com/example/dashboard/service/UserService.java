package com.example.dashboard.service;

import com.example.dashboard.domain.entity.User;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.validator.UserValidator;
import com.example.dashboard.web.representation.request.user.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserValidator validator;

    public User create(RequestUser user){
        Optional<User> verifyUser = repository.findByEmail(user.email());
        if(verifyUser.isPresent()) ; //exception

        validator.validatePayload(user);

        User newUser = new User(user);

        repository.save(newUser);

        return newUser;
    }

}
