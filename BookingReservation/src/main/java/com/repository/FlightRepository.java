package com.repository;

import com.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query(value = "select * from flight", nativeQuery = true)
    List<Flight> findallflights();

    @Query(value = "select * from flight where id=:id", nativeQuery = true)
    Flight findflightbyid(@Param("id") int id);


    @Query(value = "select count(*) from flight where booking_seatno<=:total_seats and flight_number=:flight_number",nativeQuery = true)
    int seatsavailability(@Param("total_seats") int total_seats,@Param("flight_number") int flight_number);

}
