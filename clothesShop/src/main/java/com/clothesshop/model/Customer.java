package com.clothesshop.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="customers")
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="customer_id")
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="contact_name")
    private String contactName;
    @Column(name="email")
    private String email;
    @Column(name="phone")
    private String phoneNumber;
}
