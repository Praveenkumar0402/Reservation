package com.example.BookingSystem.controller;

import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.model.JwtRequest;
import com.example.BookingSystem.model.JwtResponse;
import com.example.BookingSystem.security.JwtService;
import com.example.BookingSystem.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    JwtService jwtService;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtService.generateToken(userDetails);
        JwtResponse response = JwtResponse.builder()
                .accessToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password!!");
        }
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create")
    public User cretaeuser(@RequestBody @Valid User user) {
        return userService.create(user);
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws UserNotFoundException {
        List<User> userDto = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> update(@PathVariable("id") int id, @RequestBody @Valid User userDto) throws UserNotFoundException {
        User userDto1 = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto1);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<User> remove(@PathVariable("id") int id) throws UserNotFoundException {
        User userDto = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }


}
