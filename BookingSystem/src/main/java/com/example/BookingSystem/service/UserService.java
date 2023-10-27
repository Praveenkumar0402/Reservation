package com.example.BookingSystem.service;

import com.example.BookingSystem.AuthenticationContext.AuthUtils;
import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.entity.UserInfo;
import com.example.BookingSystem.enums.Gender;
import com.example.BookingSystem.enums.UserStatus;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthUtils authUtils;

    public List<User> findAll() throws UserNotFoundException {
        List<User> user = userRepository.findallusers();
        if (user.isEmpty()) {
            throw new UserNotFoundException("No Users found");
        } else {
            List<User> user1 = new ArrayList<>();
            for (User user2 : user) {
                User user3 = new User(user2);
                user1.add(user3);
            }
            return user1;
        }
    }

    public User updateUser(int id, User user1) throws UserNotFoundException {
        User user = authUtils.getUser();
        User user12 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not Present for updating"));
        if (user.getId() == user12.getId()) {
            user.setFirstname(user1.getFirstname());
            user.setLastname(user1.getLastname());
            user.setGender(user1.getGender());
            user.setEmail(user1.getEmail());
            user.setMobile(user1.getMobile());
            userRepository.save(user);
            return new User(user);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
    }

    public User deleteUser(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        User user1 = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not Present for deleting"));
        if (user.getId() == user1.getId()) {
            userRepository.deleteById(id);
            return new User(user);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
    }

    public User create(User user) {
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        String gender = String.valueOf((user.getGender()));
        user.setGender(check1(gender));
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMobile(user.getMobile());
        String status = String.valueOf((user.getUserstatus()));
        user.setUserstatus(check(status));
        user.setUserstatus(user.getUserstatus());
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }


    UserStatus check(String status) {
        switch (status) {
            case "ACTIVE":
                return UserStatus.ACTIVE;
            case "INACTIVE":
                return UserStatus.INACTIVE;
            default:
                throw new RuntimeException("Status is not valid");
        }
    }

    Gender check1(String gender) {
        switch (gender) {
            case "MALE":
                return Gender.MALE;
            case "FEMALE":
                return Gender.FEMALE;
            case "OTHERS":
                return Gender.OTHERS;
            default:
                throw new RuntimeException("Check the Gender");
        }
    }
}
