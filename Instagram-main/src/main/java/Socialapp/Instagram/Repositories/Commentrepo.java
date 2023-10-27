package Socialapp.Instagram.Repositories;


import Socialapp.Instagram.Entities.Comment;
import Socialapp.Instagram.Entities.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Commentrepo extends JpaRepository<Comment,Integer> {
    void deleteBypcid(int id);

    List<Comment> findBytype(Posts post);

    List<Comment> findBypcid(int id);


    List<Comment> findByuserid(int id);
}
