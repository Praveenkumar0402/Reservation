package Socialapp.Instagram.Dtos;

import Socialapp.Instagram.Entities.User;
import lombok.Data;

@Data
public class Userdto {


    private String email;
    private int following;
    private int follower;
    private int posts;
    private String phno;

}
