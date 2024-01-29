package com.oasis.taskmanagementapp.controllers;

import com.oasis.taskmanagementapp.dto.request.AuthenticationRequest;
import com.oasis.taskmanagementapp.dto.request.UserRequest;
import com.oasis.taskmanagementapp.dto.response.AuthenticationResponse;
import com.oasis.taskmanagementapp.dto.response.RegistrationResponse;
import com.oasis.taskmanagementapp.dto.response.UserResource;
import com.oasis.taskmanagementapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<RegistrationResponse> register(@Valid @RequestBody UserRequest request) {
        userService.registerUser(request);
        return new ResponseEntity<>(new RegistrationResponse("User registration successful"), CREATED);
    }

    @PostMapping(value = "/login", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        return new ResponseEntity<>(userService.loginUser(request), OK);
    }

    @GetMapping(value = "/user", headers = {"Authorization"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> viewUserProfile() {
        return new ResponseEntity<>(userService.viewUser(), OK);
    }

    @PatchMapping(value = "/user", headers = {"Authorization"}, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResource> editProfile(@Valid @RequestBody UserRequest request) {
        return new ResponseEntity<>(userService.updateUserProfile(request), OK);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
    }
}
