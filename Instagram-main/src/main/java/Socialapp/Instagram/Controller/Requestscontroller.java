//package Socialapp.Instagram.Controller;
//
//import Socialapp.Instagram.Dtos.Requestsdto;
//import Socialapp.Instagram.Entities.Requests;
//import Socialapp.Instagram.Exception.UserException;
//import Socialapp.Instagram.Services.Requestsservice;
//import io.swagger.v3.oas.annotations.Operation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/requestdetails")
//public class Requestscontroller {
//
//    @Autowired
//    Requestsservice requestsservice;
//
//
//    //Get all requests
//    @GetMapping("/requests")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @Operation(summary = "Get all requests")
//    public List<Requests> allRquests() {
//        return requestsservice.requestsAll();
//    }
//
//    @GetMapping("requestsdto")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    @Operation(summary = "Get all requestdto")
//    public List<Requestsdto> allRequestsdto() {
//        return requestsservice.requestdtoAll();
//    }
//
//    @GetMapping("/friends")
//    @PreAuthorize("hasAuthority('USER')")
//    @Operation(summary = "Requests who accepted")
//    public List<Requestsdto> allFriends() {
//        return requestsservice.friendsAll();
//    }
//
//    @DeleteMapping("/delreq/{id}")
//    @PreAuthorize("hasAuthority('USER')")
//    @Operation(summary = "Deleting the requests in pending status ")
//    public String deleteRequest(@PathVariable("id") int id) {
//        return requestsservice.requestDelete(id);
//
//    }
//
//    @GetMapping("/all")
//    public List<Requestsdto> allReq() {
//        return requestsservice.allRequestdto();
//    }
//
//    @PostMapping("/addrequests")
//    @PreAuthorize("hasAuthority('USER')")
//    public Requestsdto addRequests(@RequestBody Requests requests) throws UserException {
//        return requestsservice.requestsAdd(requests);
//    }
//
//}
//
