package com.myapp.quiz_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myapp.quiz_app.dto.AuthResponse;
import com.myapp.quiz_app.dto.AuthUser;
import com.myapp.quiz_app.service.UserService;

@RestController//for http requests
@RequestMapping("/auth")//basic mapping of url
public class AuthController {

    //obj of UserService class
    private UserService userService;
    public AuthController(UserService userService) {
        this.userService = userService;
    }
    
    /*register
    @PostMapping("/register")//request body converts jason to obj format
    public String register(@RequestBody AuthUser request)
    {
        //call register method in service class
        return userService.registerUser(request.getUsername(),request.getPassword());
    }

    //login
    @PostMapping("/login")
    public String login(@RequestBody AuthUser request)
    {
        //call login method in service class
        return userService.loginUser(request.getUsername(),request.getPassword());
    }
    */

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody AuthUser request) 
    {
        AuthResponse response = userService.registerUser(
                request.getUsername(),
                request.getPassword()
        );

        if (!response.isSuccess()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

        
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthUser request) 
    {
        AuthResponse response = userService.loginUser(
                request.getUsername(),
                request.getPassword()
        );

        if (!response.isSuccess()) {

            if (response.getMessage().equals("User not found")) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(response);
            }

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(response);
        }

        return ResponseEntity.ok(response);
    }
}
