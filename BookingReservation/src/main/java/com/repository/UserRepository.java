package com.repository;


import com.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query(value = "select * from users", nativeQuery = true)
    List<User> findallusers();

    @Query(value = "select * from users where id=:id", nativeQuery = true)
    User finduserbyid(@Param("id") int id);

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
