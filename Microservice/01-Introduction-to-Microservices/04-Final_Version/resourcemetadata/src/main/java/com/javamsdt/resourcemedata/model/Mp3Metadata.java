package com.javamsdt.resourcemedata.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "mp3_metadata")
public class Mp3Metadata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long resourceId;
    private int releaseDate;
    private double duration;
    private String audioChannelType;
    private String creator;
    private String album;
    private String artist;
    private int channels;
    private int audioSampleRate;
    private String logComment;

    @Column(name = "mp3_version")
    private String version;
    private String composer;
    private String audioCompressor;
    private String title;
    private int sampleRate;
    private String genre;
    private String contentType;
    private String albumArtist;
}
