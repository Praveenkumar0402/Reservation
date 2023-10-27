package Socialapp.Instagram.Services;


import Socialapp.Instagram.Dtos.Requestsdto;
import Socialapp.Instagram.Dtos.Userdto;
import Socialapp.Instagram.Entities.Requests;
import Socialapp.Instagram.Entities.User;
import Socialapp.Instagram.Exception.UserException;
import Socialapp.Instagram.Repositories.Requestsrepo;
import Socialapp.Instagram.Repositories.Userrepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class Requestsservice {
    @Autowired
    Requestsrepo requestsrepo;

    @Autowired
    Userrepo userrepo;

    @Autowired
    ModelMapper mapper;

    public List<Requests> requestsAll() {
        return requestsrepo.findAll();
    }

    public List<Requestsdto> friendsAll() {
        List<Requests> requests = requestsrepo.findBystatus("accepted");
        List<Requestsdto> requestsdto = Arrays.asList(mapper.map(requests, Requestsdto.class));
        return requestsdto;

    }

    public String requestDelete(int id) {
        String name = "pending";
        Requests requests1 = requestsrepo.findByStatusAndId(name, id);
        if (requests1 != null) {
            requestsrepo.deleteById(id);
            return "Deleted";
        } else {
            throw new RuntimeException("Id invalid");
        }
    }

    public List<Requestsdto> requestdtoAll() {

        List<Requests> requests = requestsrepo.findAll();
        List<Requestsdto> requestsdtos = Arrays.asList(mapper.map(requests, Requestsdto[].class));
        return requestsdtos;

    }

    public Requestsdto requestsAdd(Requests requests) throws UserException {
        int receiverId = requests.getReceiver_id();
        if (userrepo.findById(receiverId).isPresent()) {
            if (userrepo.findById(receiverId).get().getStatus().equals("active")) {
                requests.setStatus("accepted");
                requests.setSender_id(getDetails());

                User user = userrepo.findById(receiverId).get();
                user.setFollowing(user.getFollowing() + 1);
                userrepo.save(user);
                User user1=userrepo.findById(getDetails()).get();
                user1.setFollowing(user1.getFollowing()+1);
                userrepo.save(user1);

            } else {
                requests.setStatus("pending");
            }
            requestsrepo.save(requests);
            Requestsdto requestsdto = mapper.map(requests, Requestsdto.class);
            return requestsdto;
        }
        else{
            throw new UserException("Userid not Exist");
        }
    }

    public List<Requestsdto> allRequestdto() {
        List<Requests> requests = requestsrepo.findAll();
        List<Requestsdto> requestsdto = Arrays.asList(mapper.map(requests, Requestsdto[].class));
        return requestsdto;
    }


    public int getDetails() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        int id = userrepo.findByName(name).getId();
        return id;
    }


}
