package sdacademy.auctionsiteproject.dto;

import lombok.Data;
import sdacademy.auctionsiteproject.entity.Auction;
import sdacademy.auctionsiteproject.entity.Bidding;

import java.security.KeyStore;

@Data
public class BiddingRequestDTO {
    private Bidding bidding;
    private String userName;
}
