package dev.abhi.userservice.demo.Repository;

import dev.abhi.userservice.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {

    //hibernate auto implements these methods behind the scenes
    User findByEmail(String email);
}
