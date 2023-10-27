package com.service;

import com.entity.User;
import com.entity.UserInfo;
import com.exceptions.NoUsersFoundException;
import com.exceptions.ObjectNotValid;
import com.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public User createUser(User user) throws Exception {
        User user1 = new User();
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Username Already exist");
        } else {
            user1.setEmail(user.getEmail());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setRoles(user.getRoles());
            return userRepository.save(user1);
        }
    }

    public List<User> getusers() {
        return userRepository.findAll();
    }


    public List<User> findAll() throws ExpiredJwtException {
        List<User> user = userRepository.findallusers();
        if (user.isEmpty()) {
            throw new NoUsersFoundException("no users found");
        } else {
            List<User> user1 = new ArrayList<>();
            for (User user2 : user) {
                User user3 = new User(user2);
                user1.add(user3);
            }
            return user1;
        }
    }

    public User updateUser(int id, User user1) {
        User user = userRepository.finduserbyid(id);
        if (user1 == null) {
            throw new ObjectNotValid("id is not present");
        } else {
            user.setFirstname(user1.getFirstname());
            user.setLastname(user1.getLastname());
            user.setGender(user1.getGender());
            user.setEmail(user1.getEmail());
            user.setMobile(user1.getMobile());
            userRepository.save(user);
            return new User(user);
        }
    }

    public User deleteUser(int id) {
        User user = userRepository.finduserbyid(id);
        if (user == null) {
            throw new NoUsersFoundException("id is not present");
        } else {
            userRepository.deleteById(id);
            return new User(user);
        }
    }

    public User create(User user) {
        user.setFirstname(user.getFirstname());
        user.setLastname(user.getLastname());
        user.setGender(user.getGender());
        user.setEmail(user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMobile(user.getMobile());
        user.setUserstatus(user.getUserstatus());
        user.setRoles(user.getRoles());
        return userRepository.save(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(UserInfo::new).orElseThrow(() -> new RuntimeException("UserName Not Found" + username));
    }

}
