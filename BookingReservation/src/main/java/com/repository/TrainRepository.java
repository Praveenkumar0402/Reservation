package com.repository;

import com.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository<Train, Integer> {

    @Query(value = "select * from train", nativeQuery = true)
    List<Train> findAllTrains();

    @Query(value = "select * from train where id=:id", nativeQuery = true)
    Train findtrainbyid(@Param("id") int id);

    @Query(value = "select count(*) from train where booking_seatno<=:total_seats and train_number=:train_number",nativeQuery = true)
    int seatsavailability(@Param("total_seats") int total_seats,@Param("train_number") int train_number);

}
