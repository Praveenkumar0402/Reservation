package com.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "flightid")
    @SequenceGenerator(name = "flightid", sequenceName = "flightid", initialValue = 900, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userid;

    @Column(name = "flight_number")
    private int flightnumber;

    @Column(name = "booking_seatno")
    private int bookingseatno;

    @Column(name = "seats_availability")
    private int seatavailability;

    @Column(name = "totalseats")
    private int totalseats;

    @Column(name = "booking_id")
    private int bookingid;

}
