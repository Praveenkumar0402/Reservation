package Socialapp.Instagram.Repositories;


import Socialapp.Instagram.Entities.Requests;
import org.hibernate.usertype.UserTypeLegacyBridge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Requestsrepo extends JpaRepository<Requests,Integer> {


    List<Requests> findBystatus(String accepted);

    @Query(value="select*from requests where req_status=:name and request_id=:id",nativeQuery = true)
    Requests findByStatusAndId(String name, int id);
}


