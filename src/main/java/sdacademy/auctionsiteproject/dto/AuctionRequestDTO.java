package sdacademy.auctionsiteproject.dto;

import lombok.Data;
import sdacademy.auctionsiteproject.entity.Auction;

@Data
public class AuctionRequestDTO {
    private Auction auction;
    private String userName;
    private String categoryName;
}
