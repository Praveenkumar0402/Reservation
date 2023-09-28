package com.controller;

import com.dto.TrainDto;
import com.entity.Train;
import com.service.TrainService;
import jakarta.servlet.http.HttpServletResponse;
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
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<List<TrainDto>> trainsAll() {
        List<TrainDto> trainDto = trainservice.getAllTrain();
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }

    @PutMapping("/update")
    public ResponseEntity<TrainDto> trainUpdate(@RequestParam("id") int id, @RequestBody TrainDto trainDto) {
        TrainDto trainDto1 = trainservice.updateTrain(id, trainDto);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto1);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<TrainDto> trainDelete(@RequestParam("id") int id) {
        TrainDto trainDto = trainservice.deleteTrain(id);
        return ResponseEntity.status(HttpStatus.OK).body(trainDto);
    }

    @PostMapping("/create")
    public ResponseEntity<TrainDto> trainCreate(@RequestBody TrainDto trainDto) {
        TrainDto trainDto1 = trainservice.createTrain(trainDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(trainDto1);
    }

    @GetMapping("/generate")
    public void generateExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        String headerKey = "Content-Deposition";
        String headerValue = "attachment;filename=train.xlsx";
        response.setHeader(headerKey, headerValue);
        trainservice.generateExcel(response);
    }

//    @PostMapping("/add")
//    public ResponseEntity<Train> addtrain(Train train){
//        return new ResponseEntity<Train>(trainservice.savetrain(train),HttpStatus.CREATED);
//    }
}
