package com.service;

import com.entity.User;
import com.exceptions.NoUsersFoundException;
import com.exceptions.ObjectNotValid;
import com.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
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
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public List<User> findAll() {
        List<User> user = userRepository.findallusers();
        if (user.isEmpty()) {
            throw new NoUsersFoundException("no flights found");
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

    public void generateExcel(HttpServletResponse response) throws IOException {
        List<User> users = userRepository.findAll();
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        HSSFSheet sheet = hssfWorkbook.createSheet("User details");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("id");
        row.createCell(1).setCellValue("first_name");
        row.createCell(2).setCellValue("last_name");
        row.createCell(3).setCellValue("gender");
        row.createCell(4).setCellValue("email");
        row.createCell(5).setCellValue("password");
        row.createCell(6).setCellValue("mobile");
        row.createCell(7).setCellValue("userstatus");
        row.createCell(8).setCellValue("roles");
        int rowindex = 1;
        for (User user : users) {
            HSSFRow row1 = sheet.createRow(rowindex);
            row1.createCell(0).setCellValue(user.getId());
            row1.createCell(1).setCellValue(user.getFirstname());
            row1.createCell(2).setCellValue(user.getLastname());
            row1.createCell(3).setCellValue(user.getGender().ordinal());
            row1.createCell(4).setCellValue(user.getEmail());
            row1.createCell(5).setCellValue(user.getPassword());
            row1.createCell(6).setCellValue(user.getMobile());
            row1.createCell(7).setCellValue(user.getUserstatus().ordinal());
            row1.createCell(8).setCellValue(user.getRoles());
            rowindex++;
        }
        ServletOutputStream servletOutputStream = response.getOutputStream();
        hssfWorkbook.write(servletOutputStream);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //load user from database
        Optional<User> user = userRepository.findByEmail(username);
        return user.map(User::new).orElseThrow(() -> new RuntimeException("UserName Not Found"+username));
    }

}
