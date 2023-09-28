package com.dto;

import com.entity.Booking;
import com.enums.StateOfTravel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDto {

    private int Id;
    private String From;
    private String To;
    private Date Bookingdate;
    @Enumerated(EnumType.ORDINAL)
    private StateOfTravel Stateoftravel;
    private String Bookingstatus;
    private int Userid;

    public BookingDto(Booking bookingo) {
        Id=bookingo.getId();
        From = bookingo.getFrom();
        To = bookingo.getTo();
        Bookingdate = bookingo.getBookingdate();
        Stateoftravel = bookingo.getStateoftravel();
        Bookingstatus = bookingo.getBookingstatus();
        Userid = bookingo.getUserid();
    }

}
