package com.javamsdt.resource.controller;

import com.javamsdt.resource.model.audio.Song;
import com.javamsdt.resource.service.audio.SongService;
import com.javamsdt.resource.util.AppConstants;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.EncoderException;
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
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{id}")
    public Song getSong(@PathVariable() Long id) {
        return songService.getSong(id);
    }

    @GetMapping(value = "/song/{id}")
    ResponseEntity<byte[]> getSongContentById(@PathVariable("id") Long id) throws IOException, EncoderException {
        return songService.getSongContentById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> saveSong(@RequestBody() MultipartFile song) throws IOException, TikaException, SAXException {
        if (ResourceUtil.checkFileExtension(song, AppConstants.MP3_EXTENSION)) {
            return ResponseEntity.ok(songService.saveMultipartSong(song).getId());
        } else {
            String songExtension = ResourceUtil.getFileExtension(Objects.requireNonNull(song.getOriginalFilename()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Accepting MP3 file only and not %s .", songExtension));
        }
    }

    @DeleteMapping("/delete-songs")
    public ResponseEntity<?> deleteSongs(@RequestParam(name = "ids") Long[] ids) {
        return songService.deleteSongs(ids);
    }
}
