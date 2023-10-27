package com.example.BookingSystem.dto;

import com.example.BookingSystem.entity.Flight;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FlightDto {

    @NotNull
    private int Id;
    @NotNull(message = "Enter the Flight number")
    private Integer FlightNumber;
    @NotNull(message = "Enter the BookingSeat number")
    private Integer BookingSeatNo;

    private int SeatAvailability;

    private int Totalseats=40;
    @NotNull(message = "Enter the Booking id")
    private Integer Bookingid;
    @NotNull(message = "Enter the User id")
    private Integer Userid;


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
