package com.example.BookingSystem.repository;


import com.example.BookingSystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users", nativeQuery = true)
    List<User> findallusers();

    @Query(value = "select * from users where id=:id", nativeQuery = true)
    User finduserbyid(@Param("id") int id);

    Optional<User> findByEmail(String email);
}
