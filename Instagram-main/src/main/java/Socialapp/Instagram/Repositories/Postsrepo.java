package Socialapp.Instagram.Repositories;


import Socialapp.Instagram.Entities.Posts;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Repository

public interface Postsrepo extends JpaRepository<Posts,Integer> {

    List<Posts> findByuser_id(int uid);

    void deleteByuser_id(int id);
    @Query(value = "select *from posts WHERE posts_id =:postid AND user_id =:userid", nativeQuery = true)
    Posts findByIdAndUser_id(int postid, int userid);




}
