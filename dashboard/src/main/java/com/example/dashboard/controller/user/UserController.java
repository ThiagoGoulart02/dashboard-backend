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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getuser() {
        return userService.search();
    }

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(data));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable UUID id, @RequestBody @Valid RequestUserUpdate requestUserUpdate) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.update(id, requestUserUpdate.password()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.delete(id));
    }

    @PostMapping("/signin")
    public ResponseEntity searchUser(@RequestBody RequestUserSignIn data) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.signIn(data));
    }
}