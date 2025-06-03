package sdacademy.auctionsiteproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long user_id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String accountName;

    @Column(nullable = false)
    private String province;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String dateOfAccountCreation;

    @Column(nullable = false)
    private String accountStatus; //ACTIVE/INACTIVE/LOCKED

    @Column(nullable = false)
    private String type; //NORMAL or PREMIUM


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLES_ID", referencedColumnName = "ROLE_ID")})
    private List<Roles> roles;

    @OneToMany(mappedBy = "users")
    @JsonIgnore
    private List<Auction> auctions;


}
