package com.example.BookingSystem.security;

import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.entity.UserInfo;
import com.example.BookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ApplicationConfig implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserInfo::new).orElseThrow(() -> new UsernameNotFoundException("UserName Not Found " + username));
    }
}