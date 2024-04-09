package com.example.dashboard.service;

import com.example.dashboard.domain.entity.User;
import com.example.dashboard.exceptions.NotFoundException;
import com.example.dashboard.exceptions.ValidationException;
import com.example.dashboard.repository.ProductRepository;
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

    @Autowired
    private ProductRepository productRepository;

    public User create(RequestUser user) {
        if (repository.findByEmail(user.email()).isPresent()) throw new ValidationException("This user email exists.");

        validator.validatePayload(user);

        return repository.save(new User(user));
    }

    public List<User> search() {
        return repository.findAll();
    }

    public User update(UUID id, String password) {
        return repository.findById(id)
                .map(user -> {
                    if (validator.isPasswordValid(password)) {
                        user.setPassword(password);
                        return repository.save(user);
                    } else throw new ValidationException(("Invalid password."));
                })
                .orElseThrow(() -> new NotFoundException("User not found."));
    }

    public List<User> delete(UUID id) {
        return repository.findById(id)
                .map(user -> {
                    productRepository.deleteByUserId(id);
                    repository.deleteById(id);
                    return repository.findAll();
                })
                .orElseThrow(() -> new NotFoundException("User not found."));
    }


    public User signIn(RequestUserSignIn data) {
        return repository.findByEmail(data.email()).map(user -> {
                    if (user.getPassword().equals(data.password())) {
                        return user;
                    }
                    throw new ValidationException("Invalid password");
                })
                .orElseThrow(() -> new NotFoundException("Email not found."));
    }
}
