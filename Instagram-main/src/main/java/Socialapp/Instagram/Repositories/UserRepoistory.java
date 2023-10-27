package Socialapp.Instagram.Repositories;


import Socialapp.Instagram.JwtCredentials.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepoistory extends JpaRepository<Users,Integer> {
  Optional<Users> findByusername(String username);

  boolean existsByUsername(String username);
}
