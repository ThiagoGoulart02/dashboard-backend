package com.example.dashboard.service;

import com.example.dashboard.domain.entity.User;
import com.example.dashboard.exceptions.NotFoundException;
import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.validator.UserValidator;
import com.example.dashboard.web.representation.request.user.RequestUser;
import com.example.dashboard.web.representation.request.user.RequestUserDelete;
import com.example.dashboard.web.representation.request.user.RequestUserSignIn;
import com.example.dashboard.web.representation.request.user.RequestUserUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserValidator validator;

    public User create(RequestUser user) {
        Optional<User> verifyUser = repository.findByEmail(user.email());
        if (verifyUser.isPresent()) throw new ValidationException("This user email exists.");



        validator.validatePayload(user);

        User newUser = new User(user);

        repository.save(newUser);

        return newUser;
    }

    public List<User> search() {
        return repository.findAll();
    }

    public User update(RequestUserUpdate data) {
        var user = repository.findById(UUID.fromString(data.id_user()));
        if (user.isPresent()) {
            user.get().setPassword(data.password());
            if (validator.isPasswordValid(data.password())) return repository.save(user.get());
            else throw new ValidationException("Invalid password.");
        }
        throw new NotFoundException("User not found.");
    }

    public List<User> delete(RequestUserDelete data){
        var user = repository.findById(UUID.fromString(data.id_user()));
        if(user.isEmpty()){
            throw new NotFoundException("User not found.");
        }
        repository.deleteById(UUID.fromString(data.id_user()));
        return repository.findAll();
    }


    public Optional<User> signIn(RequestUserSignIn data){
        Optional<User> user = repository.findByEmail(data.email());
        if(user.isEmpty()) throw new NotFoundException("Email not found.");
        if(user.get().getPassword().equals(data.password())) return user;
        throw new ValidationException("The password is invalid.");
    }
}
