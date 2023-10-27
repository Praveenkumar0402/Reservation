package com.controller;

import com.entity.User;
import com.model.AuthenticationRequest;
import com.model.AuthenticationResponse;
import com.security.JwtService;
import com.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Auth")
public class AuthenticationController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager manager;

    @Autowired
    UserService authenticationService;
    @Autowired
    JwtService helper;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = helper.generateToken(userDetails);

        AuthenticationResponse response =
                AuthenticationResponse.builder()
                        .token(token)
                        .username(userDetails.getUsername())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create")
    public User cretaeuser(@RequestBody User user) throws Exception {
        return authenticationService.createUser(user);
    }
}