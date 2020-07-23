package dev.abhi.userservice.demo.Repository;

import dev.abhi.userservice.demo.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    //by default save method is provided
    VerificationToken findByToken(String token);
}
