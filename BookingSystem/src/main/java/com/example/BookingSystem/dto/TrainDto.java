package com.example.BookingSystem.dto;

import com.example.BookingSystem.entity.Train;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainDto {

    @NotNull
    private int Id;
    @NotNull(message = "Enter the Train number")
    private Integer TrainNumber;
    @NotNull(message = "Enter the BookingSeat number")
    private Integer BookingSeatNo;

    private int SeatAvailability;

    private int Totalseats = 50;
    @NotNull(message = "Enter the Booking id")
    private Integer Bookingid;
    @NotNull(message = "Enter the User id")
    private Integer Userid;


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
