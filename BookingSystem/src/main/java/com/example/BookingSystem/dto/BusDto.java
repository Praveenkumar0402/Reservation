package com.example.BookingSystem.dto;

import com.example.BookingSystem.entity.Bus;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusDto {

    @NotNull
    private int Id;
    @NotNull(message = "Enter the Bus number")
    private Integer BusNumber;
    @NotNull(message = "Enter the BookingSeat number")
    private Integer BookingSeatNo;

    private int SeatAvailability;

    private int Totalseats = 30;
    @NotNull(message = "Enter the Booking id")
    private Integer bookingid;
    @NotNull(message = "Enter the User id")
    private Integer Userid;


    public BusDto(Bus bus) {
        Id = bus.getId();
        BusNumber = bus.getBusnumber();
        BookingSeatNo = bus.getBookingseatno();
        SeatAvailability = bus.getSeatavailability();
        Totalseats = bus.getTotalseats();
        bookingid = bus.getBookingid();
        Userid = bus.getUserid();
    }
}
