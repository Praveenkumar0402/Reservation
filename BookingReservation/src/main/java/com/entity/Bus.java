package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bus")
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "busid")
    @SequenceGenerator(name = "busid", sequenceName = "busid", initialValue = 500, allocationSize = 1)
    @Column(name = "id")
    private int id;
    @Column(name = "bus_number")
    private int busnumber;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "booking_seatno")
    private int bookingseatno;

    @Column(name = "seats_availability")
    private int seatavailability;

    @Column(name = "totalseats")
    private int totalseats = 30;

    @Column(name = "booking_id")
    private int bookingid;

}
