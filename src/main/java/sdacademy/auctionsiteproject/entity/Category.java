package sdacademy.auctionsiteproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long category_id;

    @Column
    private String name;

    @Column
    private String description;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Auction> auctions;
}
