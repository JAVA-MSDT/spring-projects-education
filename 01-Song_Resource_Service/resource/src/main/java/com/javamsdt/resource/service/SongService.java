package com.javamsdt.resource.service;

import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import com.javamsdt.resource.metadata.mp3.service.Mp3MetadataService;
import com.javamsdt.resource.model.Song;
import com.javamsdt.resource.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final Mp3MetadataService mp3MetadataService;

    public Song getSong(Long id) {
        return findSongById(id);
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public Song saveMultipartSong(MultipartFile multipartSong) throws IOException {
        Song song = new Song();
        song.setSong(multipartSong.getBytes());
        Song saved = songRepository.save(song);
        Mp3Metadata mp3Metadata = mp3MetadataService.getMp3Metadata(multipartSong, saved.getId());
        System.out.println(mp3Metadata);
        return saved;
    }
    public Song saveSong(Song song) {
        return songRepository.save(song);
    }

    public Song updateSong(Long id, Song song) {
        Song songForUpdated = findSongById(id);
        songForUpdated.setSong(song.getSong());
        return songRepository.save(songForUpdated);
    }

    public void deleteSong(Long id) {
        songRepository.delete(findSongById(id));
    }

    private Song findSongById(Long id) {
        Optional<Song> optionalSong = songRepository.findById(id);
        System.out.println("Is Present:: " + optionalSong.isPresent());
        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
