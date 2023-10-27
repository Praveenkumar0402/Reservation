package com.example.BookingSystem.repository;

import com.example.BookingSystem.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BusRepository extends JpaRepository<Bus, Integer> {

    @Query(value = "select * from bus", nativeQuery = true)
    List<Bus> findallbuses();

    @Query(value = "select * from bus where id=:id", nativeQuery = true)
    Bus findbusbyid(@Param("id") int id);

    @Query(value = "select count(*) from bus where booking_seatno<=:total_seats and bus_number=:bus_number",nativeQuery = true)
    int seatsavailability(@Param("total_seats") int total_seats,@Param("bus_number") int bus_number);

    List<Bus> findByUserid(int id);
}
