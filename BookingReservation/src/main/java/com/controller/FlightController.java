package com.controller;

import com.dto.FlightDto;
import com.service.FlightService;
import jakarta.servlet.http.HttpServletResponse;
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
//    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<FlightDto>> getAll() {
        List<FlightDto> flightDto = flightservice.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }


    @PutMapping("/update")
    public ResponseEntity<FlightDto> update(@RequestParam("id") int id, @RequestBody FlightDto flightdto) {
        FlightDto flightDto = flightservice.updateFlight(id, flightdto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<FlightDto> delete(@RequestParam("id") int id) {
        FlightDto flightDto = flightservice.deleteFlight(id);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

    @PostMapping("/create")
    public ResponseEntity<FlightDto> create(@RequestBody FlightDto flightDto) {
        FlightDto flightDto1 = flightservice.createFlight(flightDto);
        return ResponseEntity.status(HttpStatus.OK).body(flightDto1);
    }

    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=flight.xlsx";
        response.setHeader(headerKey, headerValue);
        flightservice.generateExcel(response);
    }
}
