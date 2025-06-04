package sdacademy.auctionsiteproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "auction")
public class Auction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auction_id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private String promoted; //if account is premium or not (YES or NO)

    @Column
    private double bitNowPrice; //always display the current bit

    @Column
    private double buyNowPrice; //display the price or NO if it doesnt have a buy now option

    @Column
    private String startBiddingDate;

    @Column
    private String endBiddingDate;

    @Column
    private Long numbersOfViews;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "bidding_id")
    private Bidding bidding;
}
