package com.javamsdt.productstore.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import jdk.jfr.StackTrace;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "order_table")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "user_id")
    private long userId;
    @Column(name = "product_id")
    private long productId;
    @Column(name = "purchase_date")
    private LocalDate purchaseDate;
    @Column(name = "product_quantity")
    private int productQuantity;
    @Column(name = "total_price")
    private BigDecimal totalPrice;

}
