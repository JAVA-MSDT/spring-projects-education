package com.javamsdt.resource.model.audio;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
@Embeddable
public class Mp3Metadata {
    private int releaseDate;
    private double duration;
    private String audioChannelType;
    private String creator;
    private String album;
    private String artist;
    private int channels;
    private int audioSampleRate;
    private String logComment;
    private String version;
    private String composer;
    private String audioCompressor;
    private String title;
    private int sampleRate;
    private String genre;
    private String contentType;
    private String albumArtist;
}
