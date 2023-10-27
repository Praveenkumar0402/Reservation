package Socialapp.Instagram.JwtService;


import Socialapp.Instagram.JwtCredentials.Users;
import Socialapp.Instagram.Repositories.UserRepoistory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    private UserRepoistory userRepoistory;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users =userRepoistory.findByusername(username).orElseThrow(()-> new RuntimeException("Username not found"));
        return users;
    }
}
