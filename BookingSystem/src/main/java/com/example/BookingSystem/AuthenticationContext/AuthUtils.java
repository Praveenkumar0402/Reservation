package com.example.BookingSystem.AuthenticationContext;

import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthUtils {

    @Autowired
    UserRepository userRepository;

    public User getUser() throws UserNotFoundException {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found"));
        return user;
    }

}
