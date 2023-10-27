package Socialapp.Instagram.Services;

import Socialapp.Instagram.Dtos.Commentdto;
import Socialapp.Instagram.Dtos.Userdto;
import Socialapp.Instagram.Entities.Comment;
import Socialapp.Instagram.Entities.Type;
import Socialapp.Instagram.Entities.User;
import Socialapp.Instagram.Exception.CommentException;
import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.Repositories.Commentrepo;
import Socialapp.Instagram.Repositories.Postsrepo;
import Socialapp.Instagram.Repositories.Userrepo;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    Commentrepo commentrepo;
    @Autowired
    Postsrepo postsrepo;
    @Autowired
    Userrepo userrepo;
    @Autowired
    ModelMapper mapper;


    public List<Comment> allComments() {
        return commentrepo.findAll();
    }

    public List<Commentdto> commentdtoAll() {
        List<Comment> comments = commentrepo.findAll();
        List<Commentdto> commentdtos = Arrays.stream(mapper.map(comments, Commentdto[].class)).toList();
        return commentdtos;
    }

    public String deleteComment(int cid) throws CommentException {
        if (commentrepo.findById(cid).isPresent()) {
            commentrepo.deleteById(cid);
            commentrepo.deleteBypcid(cid);
            return "Deleted";
        } else {
            throw new CommentException("Comment Not Found");
        }

    }

    public Commentdto commentAdd(Comment comments) throws CommentException {
        comments.setUserid(getDetails());
        int id = comments.getPcid();
        var post = postsrepo.findById(id);
        var comments1 = commentrepo.findById(id);
        if (post.isPresent()) {
            comments.setType(Type.post);
        } else if (comments1.isPresent()) {
            comments.setType(Type.comment);
        } else {
            throw new CommentException("comment id  invalid");
        }
        commentrepo.save(comments);
        Commentdto commentdto = mapper.map(comments, Commentdto.class);
        return commentdto;
    }

    public int getDetails() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userrepo.findByName(name).getId();
        return id;
    }


    public List<Commentdto> userComments() {

        List<Comment> comments = commentrepo.findByuserid(getDetails());
        List<Commentdto> commentdtos = Arrays.stream(mapper.map(comments, Commentdto[].class)).toList();
        return commentdtos;
    }

    public Userdto fetchUserbyComment(int commentid) {
        Optional<Comment> comments = commentrepo.findById(commentid);
        Comment comment = comments.get();
        User user = comment.getUser();
        Userdto userdtoList = (mapper.map(user, Userdto.class));
        return userdtoList;
    }

//    public List<Commentdto> fetchComment() {
//        Optional<User> userList = userrepo.findById(getDetails());
//        User user = userList.get();
//        List<Comment> userList1 = user.getCommentList();
//        List<Commentdto> commentdtos = Arrays.stream(mapper.map(userList1, Commentdto[].class)).toList();
//        return commentdtos;
//
//    }


}


