package com.example.BookingSystem.controller;

import com.example.BookingSystem.dto.BookingDto;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
public class BookingController {

    @Autowired
    BookingService bookingservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<List<BookingDto>> getAll() throws UserNotFoundException {
        List<BookingDto> bookingDto = bookingservice.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BookingDto> update(@PathVariable("id") int id, @RequestBody @Valid BookingDto bookingDto) throws UserNotFoundException {
        BookingDto bookingDto1 = bookingservice.updateBooking(id, bookingDto);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto1);
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDto> create(@RequestBody @Valid BookingDto bookingDto) {
        BookingDto bookingDto1 = bookingservice.createBooking(bookingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BookingDto> remove(@PathVariable("id") int id) throws UserNotFoundException {
        BookingDto bookingDto = bookingservice.deleteBooking(id);
        return ResponseEntity.status(HttpStatus.OK).body(bookingDto);
    }

}