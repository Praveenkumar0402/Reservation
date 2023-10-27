//package Socialapp.Instagram.Controller;
//
//import Socialapp.Instagram.Dtos.Postsdto;
//import Socialapp.Instagram.Dtos.Userdto;
//import Socialapp.Instagram.Entities.Posts;
//import Socialapp.Instagram.Entities.User;
//import Socialapp.Instagram.Exception.UserException;
//import Socialapp.Instagram.Services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/Accountdetails")
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    //Login user details
//    @GetMapping("/userdetails")
//    @PreAuthorize("hasAuthority('USER')")
//    public Userdto fetchUserDetails() {
//        return userService.getuser();
//    }
//
//
//    //all Users
//    @GetMapping("/allusers")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<User> getAllUser() {
//        return userService.allUser();
//    }
//
//    @GetMapping("/userdto")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Userdto> allUserdto() {
//        return userService.Userdtoall();
//    }
//
//
//    //fetching through Status
//    @GetMapping("/profile/{status}")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public List<Userdto> allActiveuser(@PathVariable("status") String status) {
//        return userService.activeUserAdd(status);
//    }
//
//    //update Status
//    @PutMapping("updateStatus/{status}")
//    @PreAuthorize("hasAuthority('USER')")
//    public String updateStatus(@PathVariable("status") String status) throws UserException {
//        return userService.updateStatus(status);
//    }
//
//    //Add User
//
//    @PostMapping("/adduser")
//    @PreAuthorize("hasAuthority('USER')")
//    public Userdto addUser(@RequestBody User user) throws NoSuchFieldException {
//        return userService.userAdd(user);
//    }
//
////    //fetch all posts by User
////    @GetMapping("allposts")
////    @PreAuthorize("hasAuthority('USER')")
////    public List<Postsdto> fetchAllPostsByUser() {
////        return userService.allposts();
////    }
//
//
//}
