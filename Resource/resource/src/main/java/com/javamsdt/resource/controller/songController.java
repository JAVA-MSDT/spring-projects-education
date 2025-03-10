package com.javamsdt.resource.controller;

import com.javamsdt.resource.model.audio.Song;
import com.javamsdt.resource.service.audio.SongService;
import com.javamsdt.resource.util.AppConstants;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.tika.exception.TikaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${api.version}/songs")
@RequiredArgsConstructor
public class songController {
    private final SongService songService;

    @GetMapping
    public List<Song> getSongs() {
        return songService.getSongs();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Song> getSong(@PathVariable() Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(songService.getSong(id));
    }

    @GetMapping(value = "/content/{id}")
    public ResponseEntity<byte[]> getSongContentById(@PathVariable() Long id) {
        Song song = songService.getSong(id);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(song.getContentType()))
                .body(song.getSong());
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<Song> getSongByName(@PathVariable() String name) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(songService.findSongByName(name));
    }

    @GetMapping(value = "/content/name/{name}")
    public ResponseEntity<byte[]> getSongContentById(@PathVariable() String name) {
        Song song = songService.findSongByName(name);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(song.getContentType()))
                .body(song.getSong());
    }


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Song> saveSong(@RequestBody() MultipartFile song) throws IOException, TikaException, SAXException {
        if (ResourceUtil.checkFileExtension(song, AppConstants.MP3_EXTENSION)) {
            return ResponseEntity.ok(songService.saveMultipartSong(song));
        } else {
            String songExtension = ResourceUtil.getFileExtension(Objects.requireNonNull(song.getOriginalFilename()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Accepting MP3 file only and not %s .", songExtension));
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteSongs(@PathVariable(name = "id") Long id) {
        songService.deleteSong(id);
        return ResponseEntity.ok().build();
    }
}
