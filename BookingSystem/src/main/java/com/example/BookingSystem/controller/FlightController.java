package com.example.BookingSystem.controller;

import com.example.BookingSystem.dto.FlightDto;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.service.FlightService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    FlightService flightservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<FlightDto>> getAll() throws UserNotFoundException {
        List<FlightDto> flightDto = flightservice.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<FlightDto> update(@PathVariable("id") int id, @RequestBody @Valid FlightDto flightdto) throws UserNotFoundException {
        FlightDto flightDto = flightservice.updateFlight(id, flightdto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<FlightDto> delete(@PathVariable("id") int id) throws UserNotFoundException {
        FlightDto flightDto = flightservice.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @PostMapping("/create")
    public ResponseEntity<FlightDto> create(@RequestBody @Valid FlightDto flightDto) {
        FlightDto flightDto1 = flightservice.createFlight(flightDto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto1);
    }
}
