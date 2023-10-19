package com.javamsdt.filestore.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
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
    private String url;

    @Column
    private String extension;

    @Column(name = "content_type")
    private String contentType;
}
