package com.example.BookingSystem.entity;

import com.example.BookingSystem.enums.StateOfTravel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Entity
@Table(name = "booking")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingid")
    @SequenceGenerator(name = "bookingid", sequenceName = "bookingid", initialValue = 200, allocationSize = 1)
    @Column(name = "id")
    private int id;

    @Column(name = "from_adr")
    private String From;

    @Column(name = "to_adr")
    private String To;

    @Column(name = "booking_date")
    private Date bookingdate;

    @Column(name = "booking_status")
    private String Bookingstatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "state_of_travel")
    private StateOfTravel Stateoftravel;

    @Column(name = "user_id")
    private int userid;

}
