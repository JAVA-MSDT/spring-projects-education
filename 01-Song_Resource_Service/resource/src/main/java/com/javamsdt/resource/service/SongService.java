package com.javamsdt.resource.service;

import com.javamsdt.resource.metadata.mp3.Mp3MetadataDownstream;
import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import com.javamsdt.resource.metadata.mp3.service.Mp3MetadataService;
import com.javamsdt.resource.model.Song;
import com.javamsdt.resource.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SongService {
    private final SongRepository songRepository;
    private final Mp3MetadataService mp3MetadataService;
    private final Mp3MetadataDownstream mp3MetadataDownstream;

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
       try{
           mp3MetadataDownstream.saveMp3Metadata(mp3Metadata);
       } catch (RestClientException restClientException) {
           System.out.println("restClientException:: " + restClientException.getMessage());
       }
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
        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Song with that id: %s is Not found in DB", id)));
    }
}
