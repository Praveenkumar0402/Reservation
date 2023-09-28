package com.dto;

import com.entity.Flight;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightDto {

    private int Id;
    private int FlightNumber;
    private int BookingSeatNo;
    private int SeatAvailability;
    private int Totalseats=40;
    private int Bookingid;
    private int Userid;


    public FlightDto(Flight flight) {
        Id = flight.getId();
        FlightNumber = flight.getFlightnumber();
        BookingSeatNo = flight.getBookingseatno();
        SeatAvailability = flight.getSeatavailability();
        Totalseats = flight.getTotalseats();
        Bookingid = flight.getBookingid();
        Userid = flight.getUserid();
    }

}
