package Socialapp.Instagram.Services;


import Socialapp.Instagram.Dtos.Postsdto;
import Socialapp.Instagram.Dtos.Userdto;
import Socialapp.Instagram.Entities.Posts;
import Socialapp.Instagram.Entities.User;
import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.Repositories.Postsrepo;
import Socialapp.Instagram.Repositories.Userrepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    Userrepo userrepo;
    @Autowired
    Postsrepo postsrepo;
    @Autowired
    ModelMapper mapper;


    public List<User> allUser() {
        return userrepo.findAll();
    }

    public List<Userdto> Userdtoall() {
        List<User> users = userrepo.findAll();
        List<Userdto> userdtos = Arrays.stream(mapper.map(users, Userdto[].class)).toList();
        return userdtos;
    }

    public Userdto userAdd(User user) throws NoSuchFieldException {

        user.setPosts(0);
        user.setStatus("active");
        user.setFollowing(0);
        user.setFollower(0);
        user.setName(SecurityContextHolder.getContext().getAuthentication().getName());
        userrepo.save(user);
        Userdto userdto = mapper.map(user, Userdto.class);
        return userdto;
    }

    @Transactional
    public String updateStatus(String status) throws UserException {
        Optional<User> optionalUser = userrepo.findById(getDetails());
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (user.getStatus() == "inactive") {
                return "the given  userid already inactive";
            } else {
                user.setStatus(status);
                userrepo.save(user);
                postsrepo.deleteByuser_id(getDetails());
                return "Updated successfully";
            }
        } else {
            throw new UserException("Invalid Data");
        }
    }

    public List<Userdto> activeUserAdd(String status) {
        List<User> activeusers = userrepo.findBystatus(status);
        List<Userdto> userdto = Arrays.stream(mapper.map(activeusers, Userdto[].class)).toList();
        if (activeusers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
        } else {
            return userdto;
        }
    }

    public Userdto getuser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userrepo.findByName(name);
        Userdto userdto = mapper.map(user, Userdto.class);
        return userdto;
    }

    public int getDetails() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userrepo.findByName(name).getId();
        return id;
    }


//    public List<Postsdto> allposts() {
//        Optional<User> user = userrepo.findById(getDetails());
//        User user1 = user.get();
//        List<Posts> postsList = user1.getPostsList();
//        List<Postsdto> postsdtoList = Arrays.stream(mapper.map(postsList, Postsdto[].class)).toList();
//        return postsdtoList;
//
//    }


}
