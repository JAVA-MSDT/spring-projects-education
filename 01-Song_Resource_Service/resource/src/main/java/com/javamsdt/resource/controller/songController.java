package com.javamsdt.resource.controller;

import com.javamsdt.resource.metadata.mp3.service.Mp3MetadataService;
import com.javamsdt.resource.model.Song;
import com.javamsdt.resource.service.SongService;
import com.javamsdt.resource.util.AppConstants;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.EncoderException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("${api.version}/songs")
@RequiredArgsConstructor
public class songController {
    private final SongService songService;
    private final Mp3MetadataService mp3MetadataService;


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
        Resource song = new ByteArrayResource(songService.getSong(id).getSong());
        Metadata metadata = mp3MetadataService.getResourceMetadataFromInputStream(song.getInputStream());
        String fileName = URLEncoder.encode(metadata.get("dc:title"), StandardCharsets.UTF_8) + "." + metadata.get("xmpDM:audioCompressor").toLowerCase();
        String contentType = metadata.get("Content-Type");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(contentType));
        headers.add("Content-disposition", "inline; filename=" + fileName + "");

        return new ResponseEntity<>(songService.getSong(id).getSong(), headers, HttpStatus.OK);
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
        List<String> messages = new ArrayList<>();
        Arrays.stream(ids)
                .forEach(id -> {
                    try {
                        songService.deleteSong(id);
                    } catch (ResponseStatusException responseStatusException) {
                        messages.add(responseStatusException.getMessage());
                    }
                });
        return messages.isEmpty() ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(messages, HttpStatus.MULTI_STATUS);
    }
}
