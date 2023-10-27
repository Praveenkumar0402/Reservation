package Socialapp.Instagram.Dtos;

import Socialapp.Instagram.Entities.Posts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Postsdto {


    private int posts_id;
    private String postaddress;
    private LocalTime posted_time;
    private LocalTime deleted_time;
    private int user_id;


}
