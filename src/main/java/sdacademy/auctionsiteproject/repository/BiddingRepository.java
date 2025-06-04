package sdacademy.auctionsiteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdacademy.auctionsiteproject.entity.Bidding;

import java.util.List;

@Repository
public interface BiddingRepository extends JpaRepository<Bidding, Long> {
}
