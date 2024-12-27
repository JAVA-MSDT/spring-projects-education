package com.clothesshop.model.clothe;

import jakarta.persistence.*;
import lombok.Data;


@Entity(name = "clothe")
@Table(name = "clothe")
@Data
public class Clothe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "clothe_type")
    private ClotheType clotheType;
    @Enumerated(EnumType.STRING)
    @Column(name = "fabric")
    private Fabric fabric;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    @Enumerated(EnumType.STRING)
    @Column(name = "size")
    private Size size;
    @Enumerated(EnumType.STRING)
    @Column(name = "age_type")
    private AgeType ageType;
    @Column(name = "description")
    private String description;
    @Column(name = "images")
    private String images;
    @Column(name = "quantity_in_store")
    private int quantityInStore;
}
