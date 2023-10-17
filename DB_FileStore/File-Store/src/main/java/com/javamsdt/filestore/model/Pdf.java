package com.javamsdt.filestore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "pdf")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Pdf {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Lob
    private byte[] content;
    @Column
    private String name;
    @Column
    private String location;
    @Column
    private String extension;
}
