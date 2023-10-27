package com.example.BookingSystem.controller;

import com.example.BookingSystem.dto.BusDto;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.service.BusService;
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
@RequestMapping("/bus")
public class BusController {

    @Autowired
    BusService busservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('Admin')")
    public List<BusDto> getAll() throws UserNotFoundException {
        return busservice.findAll();
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public BusDto updates(@PathVariable("id") int id, @RequestBody @Valid BusDto busdto) throws UserNotFoundException {
        return busservice.updateBus(id,busdto);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('Admin')")
    public ResponseEntity<BusDto> remove(@PathVariable("id") int id) throws UserNotFoundException {
        BusDto busDto = busservice.deleteBus(id);
        return ResponseEntity.status(HttpStatus.OK).body(busDto);
    }

    @PostMapping("/create")
    public ResponseEntity<BusDto> create(@RequestBody @Valid BusDto busDto) {
        BusDto busDto1 = busservice.createBus(busDto);
        return ResponseEntity.status(HttpStatus.OK).body(busDto1);
    }


}
