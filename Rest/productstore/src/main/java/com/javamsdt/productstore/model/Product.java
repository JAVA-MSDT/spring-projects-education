package com.javamsdt.productstore.model;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private long productId;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "product_description")
    private String productDescription;

    @Column(name = "product_price")
    private BigDecimal productPrice;

    @Column(name = "product_quantity")
    private int productQuantity;

    @Column(name = "product_type")
    private String productType;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_images",
            joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "product_id") },
            inverseJoinColumns = { @JoinColumn(name = "image_id", referencedColumnName = "image_id") }
    )
    private Set<Image> productImages;

    @ManyToMany()
    @JoinTable(name = "user_products",
            joinColumns = { @JoinColumn(name = "product_id", referencedColumnName = "product_id", insertable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false) }
    )
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<User> users;

}
