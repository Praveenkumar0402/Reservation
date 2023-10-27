package com.controller;

import com.entity.User;
import com.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
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
//    @Autowired
//    JwtService jwtService;
//
////    private Logger logger = LoggerFactory.getLogger(UserController.class);
//
//    @PostMapping("/login")
//    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
//        this.doAuthenticate(request.getEmail(), request.getPassword());
//        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
//        String token = this.jwtService.generateToken(userDetails);
//        JwtResponse response = JwtResponse.builder()
//                .jwtToken(token)
//                .username(userDetails.getUsername()).build();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    private void doAuthenticate(String email, String password) {
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
//        try {
//            authenticationManager.authenticate(authentication);
//        } catch (BadCredentialsException e) {
//            throw new BadCredentialsException(" Invalid Username or Password!!");
//        }
//    }

//    @PostMapping("/create")
//    public User cretaeuser(@RequestBody User user) {
//        return userService.create(user);
//    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAll() throws ExpiredJwtException {
        List<User> userDto = userService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PutMapping("/update")
    public ResponseEntity<User> update(@RequestParam("id") int id, @RequestBody User userDto) {
        User userDto1 = userService.updateUser(id, userDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<User> remove(@RequestParam("id") int id) {
        User userDto = userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}
