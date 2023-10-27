package Socialapp.Instagram.JwtContoller;



import Socialapp.Instagram.Configuration.JwtService;
import Socialapp.Instagram.Demo.AuthenticationRequest;
import Socialapp.Instagram.Demo.AuthenticationResponse;
import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.JwtCredentials.Users;
import Socialapp.Instagram.JwtService.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Auth")
public class AuthenticationController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private JwtService helper;

    private Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = helper.generateToken(userDetails);

        AuthenticationResponse response =
                AuthenticationResponse.builder()
                        .token(token)
                        .username(userDetails.getUsername())
                        .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/create")
    public Users cretaeuser(@RequestBody Users users) throws UserException {
        return authenticationService.createUser(users);
    }


}
