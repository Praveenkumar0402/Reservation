package com.dto;

import com.entity.Train;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {

    private int Id;
    private int TrainNumber;
    private int BookingSeatNo;
    private int SeatAvailability;
    private int Totalseats = 50;
    private int Bookingid;
    private int Userid;


    public TrainDto(Train train) {
        Id = train.getId();
        TrainNumber = train.getTrainnumber();
        BookingSeatNo = train.getBookingseatno();
        SeatAvailability = train.getSeatavailability();
        Totalseats = train.getTotalseats();
        Bookingid = train.getBookingid();
        Userid = train.getUserid();
    }

}
