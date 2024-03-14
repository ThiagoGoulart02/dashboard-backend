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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getuser() {
        return userService.search();
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(data));
    }

    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid RequestUserUpdate data) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.update(data));
    }

    @DeleteMapping
    public ResponseEntity deleteUser(@RequestBody RequestUserDelete data){
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.delete(data));
    }

    @GetMapping("/signin")
    public ResponseEntity searchUser(@RequestBody RequestUserSignIn data){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.signIn(data));
    }
}