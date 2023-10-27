package Socialapp.Instagram.JwtService;

import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.JwtCredentials.Users;
import Socialapp.Instagram.Repositories.UserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    UserRepoistory userRepoistory;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public Users createUser(Users user) throws UserException {
        Users user1 = new Users();
        if (userRepoistory.existsByUsername(user.getUsername())) {
            throw new UserException("Username Already exist");

        } else {
            user1.setUsername(user.getUsername());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setRoles(user.getRoles());
            return userRepoistory.save(user1);
        }
    }

    public List<Users> getusers() {
        return userRepoistory.findAll();
    }
}
