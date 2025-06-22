package sdacademy.auctionsiteproject.dto;

import lombok.Data;

@Data
public class BiddingCompletedDTO {
    private String name;
    private String description;
    private String promoted;
    private String start;
    private String end;
    private double boughtWithPrice;
    private String buyer;
}
