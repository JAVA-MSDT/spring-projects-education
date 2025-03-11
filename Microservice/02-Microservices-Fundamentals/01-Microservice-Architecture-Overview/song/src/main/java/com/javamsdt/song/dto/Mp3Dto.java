package com.javamsdt.song.dto;

import com.javamsdt.song.downstream.model.Mp3Metadata;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Mp3Dto {
    private Long id;
    private String name;
    private String extension;
    private String originalName;
    private String contentType;
    private String url;
    private Mp3Metadata metadata;
    private byte[] song;

}
