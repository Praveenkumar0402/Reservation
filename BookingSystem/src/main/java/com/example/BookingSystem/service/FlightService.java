package com.example.BookingSystem.service;

import com.example.BookingSystem.AuthenticationContext.AuthUtils;
import com.example.BookingSystem.dto.FlightDto;
import com.example.BookingSystem.entity.Flight;
import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.BookingRepository;
import com.example.BookingSystem.repository.FlightRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightService {

    @Autowired
    FlightRepository flightRepository;

    @Autowired
    AuthUtils authUtils;

    public List<FlightDto> findAll() throws UserNotFoundException {
        List<Flight> flights = flightRepository.findallflights();
        if (flights.isEmpty()) {
            throw new UserNotFoundException("No flights found");
        } else {
            List<FlightDto> flightDto = new ArrayList<>();
            for (Flight flight : flights) {
                FlightDto flightDto1 = new FlightDto(flight);
                flightDto.add(flightDto1);
            }
            return flightDto;
        }
    }

    public FlightDto updateFlight(int id, FlightDto flightDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Flight flight = flightRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Flight Update"));
        if (user.getId() == flightDto.getUserid()) {
            flight.setTotalseats(flightDto.getTotalseats());
            flight.setFlightnumber(flightDto.getFlightNumber());
            flight.setBookingseatno(flightDto.getBookingSeatNo());
            flight.setSeatavailability(flightDto.getSeatAvailability());
            flight.setBookingid(flightDto.getBookingid());
            flightRepository.save(flight);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new FlightDto(flight);
    }

    public FlightDto deleteFlight(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        Flight flight = flightRepository.findById(id).orElseThrow(()->new UserNotFoundException("Id is not present for Flight delete"));;
        if (user.getId() == flight.getUserid()) {
            flightRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new FlightDto(flight);
    }

    public FlightDto createFlight(FlightDto flightDto) {
        if (flightDto.getTotalseats() < flightDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = flightRepository.seatsavailability(flightDto.getTotalseats(), flightDto.getFlightNumber());

        Flight flight = new Flight();
        flight.setTotalseats(flightDto.getTotalseats());
        flight.setFlightnumber(flightDto.getFlightNumber());
        flight.setBookingseatno(flightDto.getBookingSeatNo());
        flight.setSeatavailability((flightDto.getTotalseats() - count) - 1);
        flight.setBookingid(flightDto.getBookingid());
        flight.setUserid(flightDto.getUserid());
        flightRepository.save(flight);
        return new FlightDto(flight);
    }
}

