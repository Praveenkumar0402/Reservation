package com.example.BookingSystem.service;

import com.example.BookingSystem.AuthenticationContext.AuthUtils;
import com.example.BookingSystem.dto.TrainDto;
import com.example.BookingSystem.entity.Train;
import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.TrainRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService {

    @Autowired
    TrainRepository trainRepository;

    @Autowired
    AuthUtils authUtils;

    public List<TrainDto> getAllTrain() throws UserNotFoundException {
        List<Train> train = trainRepository.findAllTrains();
        if (train.isEmpty()) {
            throw new UserNotFoundException("No trains found");
        } else {
            List<TrainDto> trainDto = new ArrayList<>();
            for (Train train1 : train) {
                TrainDto trainDto1 = new TrainDto(train1);
                trainDto.add(trainDto1);
            }
            return trainDto;
        }
    }

    public TrainDto updateTrain(int id, TrainDto trainDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Train train = trainRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not present for Train Update"));
        ;
        if (user.getId() == train.getUserid()) {
            train.setTotalseats(trainDto.getTotalseats());
            train.setTrainnumber(trainDto.getTrainNumber());
            train.setBookingseatno(trainDto.getBookingSeatNo());
            train.setSeatavailability(trainDto.getSeatAvailability());
            train.setBookingid(trainDto.getBookingid());
            train.setUserid(trainDto.getUserid());
            trainRepository.save(train);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new TrainDto(train);
    }

    public TrainDto deleteTrain(int id) throws UserNotFoundException {
        User user = authUtils.getUser();
        Train train = trainRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Id is not present for Train delete"));
        ;
        if (user.getId() == train.getUserid()) {
            trainRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new TrainDto(train);
    }

    public TrainDto createTrain(TrainDto trainDto) {
        if (trainDto.getTotalseats() < trainDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = trainRepository.seatsavailability(trainDto.getTotalseats(), trainDto.getTrainNumber());
        Train train = new Train();
        train.setTrainnumber(trainDto.getTrainNumber());
        train.setBookingseatno(trainDto.getBookingSeatNo());
        train.setSeatavailability((trainDto.getTotalseats() - count) - 1);
        train.setTotalseats(trainDto.getTotalseats());
        train.setBookingid(trainDto.getBookingid());
        train.setUserid(trainDto.getUserid());
        trainRepository.save(train);
        return new TrainDto(train);
    }
}