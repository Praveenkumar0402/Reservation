//package Socialapp.Instagram.Controller;
//
//import Socialapp.Instagram.Dtos.Commentdto;
//import Socialapp.Instagram.Dtos.Userdto;
//import Socialapp.Instagram.Entities.Comment;
//import Socialapp.Instagram.Exception.CommentException;
//import Socialapp.Instagram.Services.CommentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/commentdetails")
//public class CommentController {
//    @Autowired
//    CommentService commentService;
//
//    // user comments
//    @GetMapping("/usercomment")
//    @PreAuthorize("hasAuthority('USER')")
//    public List<Commentdto> fetchCommentByUser() {
//        return commentService.userComments();
//    }
//
//    //all comments
//
//    @GetMapping("/allcomments")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Comment> comments() {
//        return commentService.allComments();
//    }
//
//    @GetMapping("/commentdto")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Commentdto> allCommentdto() {
//        return commentService.commentdtoAll();
//    }
//
//
//    //add comment by user
//    @PostMapping("/addcomment")
//    @PreAuthorize("hasAuthority('USER')")
//    public Commentdto addComment(@RequestBody Comment comment) throws CommentException {
//        return commentService.commentAdd(comment);
//    }
//
//    //delete comment by user
//    @DeleteMapping("/delcomment/{commentid}")
//    @PreAuthorize("hasAuthority('USER')")
//    public String deleteComment(@PathVariable("commentid") int cid) throws CommentException {
//        return commentService.deleteComment(cid);
//    }
//
//    //FETCH ALL COMMENTS BY ONE USER
//    @GetMapping("/com")
//    @PreAuthorize("hasAuthority('USER')")
//    public List<Commentdto> fetchAllCommentsByOneUser() {
//        return commentService.fetchComment();
//    }
//
//    //fetch  user by commentid
//    @GetMapping("/com/{commentid}")
//    @PreAuthorize("hasAuthority('USER')")
//    public Userdto fetchUserFromComment(@PathVariable("commentid") int commentid) {
//        return commentService.fetchUserbyComment(commentid);
//    }
//
//}
