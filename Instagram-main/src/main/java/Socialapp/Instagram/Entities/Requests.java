package Socialapp.Instagram.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "requests")
@Entity
@Data
public class Requests {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int request_id;

    private int sender_id;

    private int receiver_id;

    @Column(name = "req_status")
    private String status;


}


