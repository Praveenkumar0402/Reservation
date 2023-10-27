package com.example.BookingSystem.repository;

import com.example.BookingSystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

    @Query(value = "select * from booking", nativeQuery = true)
    List<Booking> findAllBookings();

    @Query(value = "select * from booking where id=:id", nativeQuery = true)
    Booking findbookingbyid(@Param("id") int id);

    List<Booking> findByUserid(int id);

}
