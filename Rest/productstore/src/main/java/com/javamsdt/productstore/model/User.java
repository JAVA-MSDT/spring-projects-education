package com.javamsdt.productstore.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_images",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "image_id", referencedColumnName = "image_id") }
    )
    @EqualsAndHashCode.Exclude
    private Set<Image> userImages;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "user_products",
            joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "product_id") }
    )
    private Set<Product> products;
}
