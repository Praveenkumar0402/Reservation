package Socialapp.Instagram.Dtos;

import Socialapp.Instagram.Entities.Requests;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data

public class Requestsdto {

    private int request_id;

    private int sender_id;

    private int receiver_id;

    private String status;





}
