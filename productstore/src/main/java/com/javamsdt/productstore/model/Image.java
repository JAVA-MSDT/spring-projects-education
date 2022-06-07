package com.javamsdt.productstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "image")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long imageId;

    @Column(name = "image_title")
    private String imageTitle;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "image_alt")
    private String imageAlt;

    @ManyToOne
    @JoinTable(name =  "user_images",
            joinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")}
    )
    private User user;

    @ManyToOne
    @JoinTable(name =  "product_images",
            joinColumns = {@JoinColumn(name = "image_id", referencedColumnName = "image_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id", referencedColumnName = "product_id")}
    )
    private Product product;

}
