package sdacademy.auctionsiteproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

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

    @Column(nullable = false)
    private boolean isCompleted = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "auction", cascade = CascadeType.ALL)
    private List<Bidding> biddings;

    @JsonProperty("isCompleted")
    public boolean isCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}
