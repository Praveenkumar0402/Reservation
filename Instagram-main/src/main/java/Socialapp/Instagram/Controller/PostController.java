//package Socialapp.Instagram.Controller;
//
//import Socialapp.Instagram.Dtos.Commentdto;
//import Socialapp.Instagram.Dtos.Postsdto;
//import Socialapp.Instagram.Dtos.Userdto;
//import Socialapp.Instagram.Entities.Posts;
//import Socialapp.Instagram.Exception.PostsException;
//import Socialapp.Instagram.Services.PostService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/postdetails")
//public class PostController {
//
//    @Autowired
//    PostService postService;
//
//    @GetMapping("/userpost")
//    @PreAuthorize("hasAuthority('USER')")
//    public List<Postsdto> fetchUserPosts() {
//        return postService.userPosts();
//    }
//
//
//    //all posts
//    @GetMapping("allposts")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Posts> getAllPosts() {
//        return postService.allPosts();
//    }
//
//    @GetMapping("/postsdto")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Postsdto> allPostsdto() {
//        return postService.postsdtoAll();
//    }
//
//    // add post
//
//    @PostMapping("/addposts")
//    @PreAuthorize("hasAuthority('USER')")
//    public Postsdto addPosts(@RequestBody Posts posts) {
//        return postService.postsAdd(posts);
//    }
//
//    // delete post by postid
//    @DeleteMapping("/delpost/{postid}")
//    @PreAuthorize("hasAuthority('USER')")
//    public String delPosts(@PathVariable("postid") int postid) throws PostsException {
//        return postService.postsDelete(postid);
//    }
//
//    //fetch User By postid
//    @GetMapping("/userdetails/{postid}")
//    @PreAuthorize("hasAuthority('USER')")
//    public Userdto fetchUserByPostId(@PathVariable("postid") int postid) {
//        return postService.allusers(postid);
//    }
//
//
//    //fetch all comments on post by postid
//
//    @GetMapping("/allcomments/{postid}")
//    @PreAuthorize("hasAuthority('USER')")
//    public List<Commentdto> fetchAllComments(@PathVariable("postid") int postid) {
//        return postService.allComments(postid);
//    }
//
//}
