package com.example.dashboard.controller.user;

import com.example.dashboard.domain.entity.User;
import com.example.dashboard.repository.UserRepository;
import com.example.dashboard.web.representation.request.RequestUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;
    @GetMapping
    public ResponseEntity getuser(){
     var user = repository.findAll();
     return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data){
        //User newUser = new User(data);
        repository.save(new User(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid RequestUser data){
        Optional<User> user = repository.findById(data.id());
        return ResponseEntity.ok(user);
    }
}
