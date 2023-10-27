package Socialapp.Instagram.Dtos;

import Socialapp.Instagram.Entities.Comment;
import lombok.Data;

@Data
public class Commentdto {

    private int id;
    private  String comment;
    private String type;
    private int pcid;
    private int userid;


}
