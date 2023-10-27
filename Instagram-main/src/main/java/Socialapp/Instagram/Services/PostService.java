package Socialapp.Instagram.Services;

import Socialapp.Instagram.Dtos.Commentdto;
import Socialapp.Instagram.Dtos.Postsdto;
import Socialapp.Instagram.Dtos.Userdto;
import Socialapp.Instagram.Entities.Comment;
import Socialapp.Instagram.Entities.Posts;
import Socialapp.Instagram.Entities.User;
import Socialapp.Instagram.Exception.PostsException;
import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.Repositories.Commentrepo;
import Socialapp.Instagram.Repositories.Postsrepo;
import Socialapp.Instagram.Repositories.Userrepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    Postsrepo postsrepo;

    @Autowired
    Userrepo userrepo;
    @Autowired
    Commentrepo commentrepo;
    @Autowired
    ModelMapper mapper;

    public List<Posts> allPosts() {
        return postsrepo.findAll();
    }

    public List<Postsdto> postsdtoAll() {
        List<Posts> posts = postsrepo.findAll();
        List<Postsdto> postsdtos = Arrays.stream(mapper.map(posts, Postsdto[].class)).toList();
        return postsdtos;
    }

    public Postsdto postsAdd(Posts post) {
        post.setPosted_time(LocalTime.now());
        post.setDeleted_time(LocalTime.of(9, 56, 23));
        post.setUser_id(getDetails());
        postsrepo.save(post);
        User user = userrepo.findById(getDetails()).get();
        user.setPosts(user.getPosts() + 1);
        userrepo.save(user);

        Postsdto postsdto = mapper.map(post, Postsdto.class);
        return postsdto;
    }

    public String postsDelete(int postid) throws PostsException {
        int userid = getDetails();
        Posts posts = postsrepo.findByIdAndUser_id(postid, userid);
        if (posts != null) {
            postsrepo.deleteById(postid);
            commentrepo.deleteBypcid(postid);
            User user = userrepo.findById(getDetails()).get();
            user.setPosts(user.getPosts() - 1);
            userrepo.save(user);
            return "Deleted";
        } else {
            throw new PostsException("Invalid Post number");
        }

    }


    public List<Postsdto> userPosts() {
        List<Posts> posts = postsrepo.findByuser_id(getDetails());
        List<Postsdto> postsdto = Arrays.stream(mapper.map(posts, Postsdto[].class)).toList();
        return postsdto;
    }


    public int getDetails() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userrepo.findByName(name).getId();
        return id;
    }


    public Userdto allusers(int postid) {
        Optional<Posts> posts = postsrepo.findById(postid);
        Posts posts1 = posts.get();
        User user = posts1.getUser();
        Userdto userdto = mapper.map(user, Userdto.class);
        return userdto;
    }

    public List<Commentdto> allComments(int postid) {
        Optional<Posts> posts = postsrepo.findById(postid);
        Posts post1 = posts.get();
        List<Comment> postsList = post1.getCommentList();
        List<Commentdto> commentdtoList = Arrays.stream(mapper.map(postsList, Commentdto[].class)).toList();
        return commentdtoList;
    }
}
