package com.javamsdt.resource.service.audio;

import com.javamsdt.resource.model.audio.Song;
import com.javamsdt.resource.repository.SongRepository;
import com.javamsdt.resource.util.ResourceCompressor;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private final SongRepository songRepository;

    public Song getSong(Long id) {
        return findSongById(id);
    }

    public List<Song> getSongs() {
        return songRepository.findAll()
                .stream()
                .peek(song -> song.setSong(ResourceCompressor.decompressResource(song.getSong())))
                .collect(Collectors.toList());
    }


    public Song saveMultipartSong(MultipartFile multipartSong) throws IOException {
        String imageOriginalName = multipartSong.getOriginalFilename();
        Song song = new Song();

        song.setName(ResourceUtil.getFileName(Objects.requireNonNull(imageOriginalName)));
        song.setExtension(ResourceUtil.getFileExtension(Objects.requireNonNull(imageOriginalName)));
        song.setOriginalName(imageOriginalName);
        song.setContentType(multipartSong.getContentType());
        song.setSong(ResourceCompressor.compressResource(multipartSong.getBytes()));

        Song savedSong = songRepository.save(song);
        savedSong.setSong(ResourceCompressor.decompressResource(savedSong.getSong()));
        return savedSong;
    }

    public void deleteSong(Long id) {
        songRepository.delete(findSongById(id));
    }
    public Song findSongByName(String name) {
        Song song = songRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Song with that Name: %s is Not found in DB", name)));
        song.setSong(ResourceCompressor.decompressResource(song.getSong()));
        return song;
    }
    private Song findSongById(Long id) {
        Song song = songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Song with that id: %s is Not found in DB", id)));
        song.setSong(ResourceCompressor.decompressResource(song.getSong()));
        return song;
    }

}
