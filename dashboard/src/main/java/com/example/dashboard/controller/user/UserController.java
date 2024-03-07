package com.example.dashboard.controller.user;

import com.example.dashboard.domain.entity.User;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.service.UserService;
import com.example.dashboard.web.representation.request.user.RequestUser;
import com.example.dashboard.web.representation.request.user.RequestUserDelete;
import com.example.dashboard.web.representation.request.user.RequestUserSignIn;
import com.example.dashboard.web.representation.request.user.RequestUserUpdate;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getuser() {
        var user = repository.findAll();
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data) {
        User user = userService.create(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid RequestUserUpdate data) {
        var user = repository.findById(UUID.fromString(data.id()));
        if (user.isPresent()) {
            user.get().setPassword(data.password());
            repository.save(user.get());
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody RequestUserDelete data){
        repository.deleteById(UUID.fromString(data.id()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/signin")
    public ResponseEntity searchUser(@RequestBody RequestUserSignIn data){
        Optional<User> user = repository.findByEmail(data.email());
        if(user.isPresent() && user.get().getPassword().equals(data.password())) return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        else return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
