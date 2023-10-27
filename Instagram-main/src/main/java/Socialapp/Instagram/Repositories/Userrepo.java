package Socialapp.Instagram.Repositories;


import Socialapp.Instagram.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Userrepo extends JpaRepository<User,Integer> {
    List<User> findBystatus(String active);

    boolean existsByName(String name);
    User findByName(String username);


}
