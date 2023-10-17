package com.javamsdt.filestore.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PdfDto {

    private long id;

    private byte[] content;

    private String name;

    private String location;

    private String extension;
}
