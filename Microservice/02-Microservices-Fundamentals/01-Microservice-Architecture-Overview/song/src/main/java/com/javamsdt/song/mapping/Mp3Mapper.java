package com.javamsdt.song.mapping;

import com.javamsdt.song.downstream.model.Mp3Metadata;
import com.javamsdt.song.dto.Mp3Dto;
import com.javamsdt.song.model.Song;
import org.springframework.stereotype.Component;

@Component
public class Mp3Mapper {

    public Mp3Dto toMp3Dto(Mp3Metadata mp3Metadata, Song song) {
        Mp3Dto mp3Dto = new Mp3Dto();
        mp3Dto.setId(song.getId());
        mp3Dto.setName(song.getName());
        mp3Dto.setOriginalName(song.getOriginalName());
        mp3Dto.setUrl(song.getUrl());
        mp3Dto.setContentType(song.getContentType());
        mp3Dto.setExtension(song.getExtension());
        mp3Dto.setMetadata(mp3Metadata);
        mp3Dto.setSong(song.getSong());
        return mp3Dto;
    }
}
