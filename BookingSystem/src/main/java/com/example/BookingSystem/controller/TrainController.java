package com.example.BookingSystem.controller;

import com.example.BookingSystem.dto.TrainDto;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.service.TrainService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/train")
public class TrainController {

    @Autowired
    TrainService trainservice;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<List<TrainDto>> trainsAll() throws UserNotFoundException {
        List<TrainDto> trainDto = trainservice.getAllTrain();
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }


    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<TrainDto> trainUpdate(@PathVariable("id") int id, @RequestBody @Valid TrainDto trainDto) throws UserNotFoundException {
        TrainDto trainDto1 = trainservice.updateTrain(id, trainDto);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto1);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('User')")
    public ResponseEntity<TrainDto> trainDelete(@PathVariable("id") int id) throws UserNotFoundException {
        TrainDto trainDto = trainservice.deleteTrain(id);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }

    @PostMapping("/create")
    public ResponseEntity<TrainDto> trainCreate(@RequestBody @Valid TrainDto trainDto) {
        TrainDto trainDto1 = trainservice.createTrain(trainDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainDto1);
    }



}
