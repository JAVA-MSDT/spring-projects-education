package com.clothesshop.model.user;


import com.clothesshop.model.clothe.Clothe;
import com.clothesshop.model.user.security.UserSecurity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phoneNumber;
    @Column(name = "address")
    private String address;
    @Column(name = "profile_picture_url")
    private String profilePictureUrl;
    @Column(name = "banner_picture_url")
    private String bannerPictureUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_clothe",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "clothe_id")
    )
    @ToString.Exclude
    private List<Clothe> clothes;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_security_id")
    @ToString.Exclude
    private UserSecurity userSecurity;
}
