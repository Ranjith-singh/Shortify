package com.url.shortener.controller;

import com.url.shortener.models.User;
import com.url.shortener.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shortener.dtos.EmailRequest;
import com.url.shortener.dtos.LoginRequest;
import com.url.shortener.dtos.RegisterRequest;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    // @PostMapping("/hello")
    // public ResponseEntity<?> hello(){
    //     return ResponseEntity.ok("User Registered Succesfully");
    // }

    @PostMapping("/public/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        // System.out.println("Login attempt with email: " + loginRequest.getEmail());
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
    }
    

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setRole("ROLE_USER");
        // if(userService.userExists(user)){
        //     return ResponseEntity.badRequest().body("User Already Exists");
        // }
        if(userService.emailExists(user)){
            return ResponseEntity.badRequest().body("Email Already Exists");
        }
        userService.registerUser(user);
        return ResponseEntity.ok("User Registered Successfully");
    }

    @PostMapping("/public/email")
    public ResponseEntity<?> email(@RequestBody EmailRequest emailRequest) throws Exception{
        if(!userService.emailExists(emailRequest.getReceiverEmail())){
            return ResponseEntity.badRequest().body("Email doesn't exists in the Database, Please register");
        }
        userService.sendEmail(emailRequest);
        return ResponseEntity.ok("Email sent!!!");
    }
    
    @PostMapping("/public/forgotPassword")
    public ResponseEntity<?> forgotPassword(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.getEmail());
        if(user==null){
            return ResponseEntity.badRequest().body("Email not found");
        }
        userService.changePassword(user, loginRequest);
        return ResponseEntity.ok("Password changed!!!");
    }
    
}
