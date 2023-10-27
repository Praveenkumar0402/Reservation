package com.example.BookingSystem.service;

import com.example.BookingSystem.AuthenticationContext.AuthUtils;
import com.example.BookingSystem.dto.BookingDto;
import com.example.BookingSystem.entity.Booking;
import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.enums.StateOfTravel;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.BookingRepository;
import com.example.BookingSystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthUtils authUtils;

    public List<BookingDto> findAll() throws UserNotFoundException {
        List<Booking> bookings = bookingRepository.findAllBookings();
        if (bookings.isEmpty()) {
            throw new UserNotFoundException("Sorry no bookings found");
        } else {
            List<BookingDto> bookingDtos = new ArrayList<>();
            for (Booking booking : bookings) {
                BookingDto bookingDto = new BookingDto(booking);
                bookingDtos.add(bookingDto);
            }
            return bookingDtos;
        }
    }

    public BookingDto updateBooking(int id, BookingDto bookingDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Booking Update"));
        if (user.getId() == booking.getUserid()) {
            booking.setFrom(bookingDto.getFrom());
            booking.setTo(bookingDto.getTo());
            booking.setBookingdate(bookingDto.getBookingdate());
            booking.setBookingstatus(bookingDto.getBookingstatus());
            booking.setStateoftravel(bookingDto.getStateoftravel());
            bookingRepository.save(booking);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BookingDto(booking);

    }

    public BookingDto deleteBooking(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        Booking booking = bookingRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Booking Update"));;
        if (user.getId() == booking.getUserid()) {
            bookingRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BookingDto(booking);
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setFrom(bookingDto.getFrom());
        booking.setTo(bookingDto.getTo());
        booking.setBookingdate(bookingDto.getBookingdate());
        String way=String.valueOf(bookingDto.getStateoftravel());
        booking.setStateoftravel(check(way));
        booking.setBookingstatus(bookingDto.getBookingstatus());
        booking.setUserid(bookingDto.getUserid());
        bookingRepository.save(booking);
        return new BookingDto(booking);
    }

    StateOfTravel check(String way){
        switch (way){
            case "BUS" :return StateOfTravel.BUS;
            case "FLIGHT" :return StateOfTravel.FLIGHT;
            case "TRAIN" :return StateOfTravel.TRAIN;
            default: throw new RuntimeException("Check the state of travel");
        }
    }

}
