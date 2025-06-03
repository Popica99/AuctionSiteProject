package sdacademy.auctionsiteproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sdacademy.auctionsiteproject.entity.Auction;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    List<Auction> findAllByName(String name);
}
