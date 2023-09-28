package com.dto;

import com.entity.Bus;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BusDto {

    private int Id;
    private int BusNumber;
    private int BookingSeatNo;
    private int SeatAvailability;
    private int Totalseats = 30;
    private int Bookingid;
    private int Userid;


    public BusDto(Bus bus) {
        Id = bus.getId();
        BusNumber = bus.getBusnumber();
        BookingSeatNo = bus.getBookingseatno();
        SeatAvailability = bus.getSeatavailability();
        Totalseats = bus.getTotalseats();
        Bookingid = bus.getBookingid();
        Userid = bus.getUserid();
    }
}
