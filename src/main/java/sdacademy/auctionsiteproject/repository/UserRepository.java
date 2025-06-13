package sdacademy.auctionsiteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdacademy.auctionsiteproject.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountName(String accountName);
    Optional<User> findByEmail(String email);
}
