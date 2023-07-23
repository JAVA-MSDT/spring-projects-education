package com.javamsdt.resource.service.audio;

import com.javamsdt.resource.model.audio.Mp3Metadata;
import com.javamsdt.resource.model.audio.Song;
import com.javamsdt.resource.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.javamsdt.resource.util.ResourceUtil.getMp3Extension;
import static com.javamsdt.resource.util.ResourceUtil.getTitle;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private final SongRepository songRepository;
    private final Mp3MetadataService mp3MetadataService;

    public Song getSong(Long id) {
        return findSongById(id);
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public ResponseEntity<byte[]> getSongContentById(Long id) throws IOException {
        Song song = findSongById(id);
        Resource songResource = new ByteArrayResource(song.getSong());
        HttpHeaders headers = getHttpHeadersForMp3Metadata(song);
        return new ResponseEntity<>(songResource.getContentAsByteArray(), headers, HttpStatus.OK);
    }

    public Song saveMultipartSong(MultipartFile multipartSong) throws IOException {
        Mp3Metadata mp3Metadata = mp3MetadataService.getMp3Metadata(multipartSong.getInputStream());
        System.out.println(mp3Metadata);
        Song song = new Song();
        song.setSong(multipartSong.getBytes());
        song.setMp3Metadata(mp3Metadata);
        return songRepository.save(song);
    }

    public ResponseEntity<?> deleteSongs(Long[] ids) {
        List<String> messages = new ArrayList<>();
        Arrays.stream(ids)
                .forEach(id -> {
                    try {
                        deleteSong(id);
                    } catch (ResponseStatusException responseStatusException) {
                        messages.add(responseStatusException.getMessage());
                    }
                });
        return messages.isEmpty() ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(messages, HttpStatus.MULTI_STATUS);
    }

    public void deleteSong(Long id) {
        songRepository.delete(findSongById(id));
    }

    private Song findSongById(Long id) {
        return songRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Song with that id: %s is Not found in DB", id)));
    }

    private HttpHeaders getHttpHeadersForMp3Metadata(Song song) {
        HttpHeaders headers = new HttpHeaders();
        String fileName = URLEncoder.encode(getTitle(song.getMp3Metadata().getTitle()), StandardCharsets.UTF_8) + "." + getMp3Extension(song.getMp3Metadata().getAudioCompressor());
        String contentType = song.getMp3Metadata().getContentType();
        headers.setContentType(MediaType.valueOf(contentType));
        headers.add("Content-disposition", "inline; filename=" + fileName + "");

        return headers;
    }


}
