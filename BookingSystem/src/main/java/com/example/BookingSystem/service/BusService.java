package com.example.BookingSystem.service;


import com.example.BookingSystem.AuthenticationContext.AuthUtils;
import com.example.BookingSystem.dto.BusDto;
import com.example.BookingSystem.entity.Bus;
import com.example.BookingSystem.entity.User;
import com.example.BookingSystem.exceptions.UserNotFoundException;
import com.example.BookingSystem.repository.BookingRepository;
import com.example.BookingSystem.repository.BusRepository;
import com.example.BookingSystem.repository.UserRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusService {

    @Autowired
    BusRepository busRepository;
    @Autowired
    AuthUtils authUtils;


    public List<BusDto> findAll() throws UserNotFoundException {
        List<Bus> bus = busRepository.findallbuses();
        if (bus.isEmpty()) {
            throw new UserNotFoundException("No buses found");
        } else {
            List<BusDto> busDto = new ArrayList<>();
            for (Bus buses : bus) {
                BusDto busDto1 = new BusDto(buses);
                busDto.add(busDto1);
            }
            return busDto;
        }
    }


    public BusDto updateBus(int id, BusDto busDto) throws UserNotFoundException {
        User user = authUtils.getUser();
        Bus bus = busRepository.findById(id).orElseThrow(() -> new UserNotFoundException("Bus id not FOUND"));
        if (user.getId() == bus.getUserid()) {
            bus.setTotalseats(busDto.getTotalseats());
            bus.setBusnumber(busDto.getBusNumber());
            bus.setBookingseatno(busDto.getBookingSeatNo());
            bus.setSeatavailability(busDto.getSeatAvailability());
            bus.setBookingid(busDto.getBookingid());
            busRepository.save(bus);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BusDto(bus);
    }


    public BusDto deleteBus(int id) throws UserNotFoundException {
        User user=authUtils.getUser();
        Bus bus = busRepository.findById(id).orElseThrow(()->new UserNotFoundException("Bus id not found"));
        if (user.getId()==bus.getUserid()) {
            busRepository.deleteById(id);
        } else {
            throw new UserNotFoundException("User Mismatch");
        }
        return new BusDto(bus);
    }

    public BusDto createBus(BusDto busDto) {
        if (busDto.getTotalseats() < busDto.getBookingSeatNo()) {
            throw new RuntimeException("Invalid Seat Number");
        }
        int count = busRepository.seatsavailability(busDto.getTotalseats(), busDto.getBusNumber());
        Bus bus = new Bus();
        bus.setTotalseats(busDto.getTotalseats());
        bus.setBusnumber(busDto.getBusNumber());
        bus.setBookingseatno(busDto.getBookingSeatNo());
        bus.setSeatavailability((busDto.getTotalseats() - count) - 1);
        bus.setBookingid(busDto.getBookingid());
        bus.setUserid(busDto.getUserid());
        busRepository.save(bus);
        return new BusDto(bus);

    }
}
