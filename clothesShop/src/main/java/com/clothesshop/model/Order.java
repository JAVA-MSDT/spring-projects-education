package com.clothesshop.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private long id;
    @Column(name="customer_id")
    private long customerId;
    @Column(name="order_info")
    private String orderInfo;
}
