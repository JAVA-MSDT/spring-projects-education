package com.javamsdt.song.controller;

import com.javamsdt.song.model.Song;
import com.javamsdt.song.service.SongService;
import com.javamsdt.song.util.AppConstants;
import com.javamsdt.song.util.SongUtil;
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
@RequestMapping("${api.version}/mp3s")
@RequiredArgsConstructor
public class Mp3Controller {
    private final SongService songService;

    @GetMapping
    public List<Song> getAllMp3() {
        return songService.getSongs();
    }

    @GetMapping("/{id}")
    public Song getMp3(@PathVariable() Long id) {
        return songService.getSong(id);
    }

    @GetMapping(value = "/mp3/{id}")
    ResponseEntity<byte[]> getMp3ContentById(@PathVariable("id") Long id) throws IOException, EncoderException {
        return songService.getSongContentById(id);
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Long> saveMp3(@RequestBody() MultipartFile song) throws IOException, TikaException, SAXException {
        if (SongUtil.checkFileExtension(song, AppConstants.MP3_EXTENSION)) {
            return ResponseEntity.ok(songService.saveMultipartSong(song).getId());
        } else {
            String songExtension = SongUtil.getFileExtension(Objects.requireNonNull(song.getOriginalFilename()));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, String.format("Accepting MP3 file only and not %s .", songExtension));
        }
    }

    @DeleteMapping("/delete-mp3s")
    public ResponseEntity<?> deleteMp3s(@RequestParam(name = "ids") Long[] ids) {
        return songService.deleteSongs(ids);
    }
}
