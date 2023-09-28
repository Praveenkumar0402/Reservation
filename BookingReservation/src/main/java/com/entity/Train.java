package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "trainid")
    @SequenceGenerator(name = "trainid", sequenceName = "trainid", initialValue = 700, allocationSize = 1)
    @Column(name = "train_id")
    private int id;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "train_number")
    private int trainnumber;

    @Column(name = "booking_seatno")
    private int bookingseatno;

    @Column(name = "seats_availability")
    private int seatavailability;

    @Column(name = "totalseats")
    private int totalseats;

    @Column(name = "booking_id")
    private int bookingid;

}

