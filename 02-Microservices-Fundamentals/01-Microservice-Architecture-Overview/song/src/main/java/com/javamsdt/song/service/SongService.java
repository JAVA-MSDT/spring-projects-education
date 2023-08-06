package com.javamsdt.song.service;

import com.javamsdt.song.downstream.api.Mp3MetadataApi;
import com.javamsdt.song.downstream.api.SongApi;
import com.javamsdt.song.downstream.model.Mp3Metadata;
import com.javamsdt.song.downstream.service.Mp3MetadataService;
import com.javamsdt.song.model.Song;
import com.javamsdt.song.repository.SongRepository;
import com.javamsdt.song.util.SongUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.metadata.Metadata;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static com.javamsdt.song.util.SongUtil.getMp3Extension;
import static com.javamsdt.song.util.SongUtil.getTitle;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private static final String REST_CLIENT_EXCEPTION_MESSAGE = "restClientException While";
    private final SongRepository songRepository;
    private final Mp3MetadataService mp3MetadataService;
    private final Mp3MetadataApi mp3MetadataApi;

    private final SongApi songApi;

    public Song getSong(Long id) {
        Song song = songApi.getSongByResourceId(1L);
        List<Song> songs = songApi.getSongsByExtension(".mp3");
        System.out.println("Songs:: " + songs.size());
        System.out.println(song);
        return findSongById(id);
    }

    public List<Song> getSongs() {
        return songRepository.findAll();
    }

    public ResponseEntity<byte[]> getSongContentById(Long id) throws IOException {
        Resource song = new ByteArrayResource(findSongById(id).getSong());
        HttpHeaders headers = getHttpHeadersForMp3Metadata(song, id);
        return new ResponseEntity<>(song.getContentAsByteArray(), headers, HttpStatus.OK);
    }

    public Song saveMultipartSong(MultipartFile multipartSong) throws IOException {
        Song song = songFromMultipartFile(multipartSong);

        Song saved = songRepository.save(song);
        Mp3Metadata mp3Metadata = mp3MetadataService.getMp3Metadata(multipartSong.getInputStream(), saved.getId());
        try {
            Song toSongApi = songApi.saveSong(multipartSong);
            System.out.println("Song API:: " + toSongApi);
            mp3MetadataApi.saveMp3Metadata(mp3Metadata);
        } catch (RestClientException restClientException) {
            log.error(REST_CLIENT_EXCEPTION_MESSAGE + " saveMp3Metadata:: " + restClientException.getMessage(), restClientException);
        }
        return saved;
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
        try {
            List<String> deletedMetadata = mp3MetadataApi.deleteMp3Metadata(ids);
            if (!deletedMetadata.isEmpty()) {
                messages.addAll(deletedMetadata);
            }
        } catch (RestClientException restClientException) {
            log.error(REST_CLIENT_EXCEPTION_MESSAGE + " deletedMetadata:: " + restClientException.getMessage(), restClientException);
        }
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

    private HttpHeaders getHttpHeadersForMp3Metadata(Resource song, Long resourceId) {
        HttpHeaders headers = new HttpHeaders();
        String fileName = "";
        String contentType = "";
        try {
            Mp3Metadata mp3Metadata = mp3MetadataApi.getMp3MetadataByResourceId(resourceId);
            fileName = URLEncoder.encode(getTitle(mp3Metadata.getTitle()), StandardCharsets.UTF_8) + "." + getMp3Extension(mp3Metadata.getAudioCompressor());
            contentType = mp3Metadata.getContentType();

        } catch (RestClientException restClientException) {
            log.error(REST_CLIENT_EXCEPTION_MESSAGE + " getMp3MetadataByResourceId:: " + restClientException.getMessage(), restClientException);
            try {
                Metadata metadata = mp3MetadataService.getResourceMetadataFromInputStream(song.getInputStream());
                fileName = URLEncoder.encode(getTitle(metadata.get("dc:title")), StandardCharsets.UTF_8) + "." + getMp3Extension(metadata.get("xmpDM:audioCompressor"));
                contentType = metadata.get("Content-Type");
            } catch (IOException e) {
                log.error("IOException while reading inputStream for getting Mp3Metadata:: " + e.getMessage(), e);
            }

        }
        headers.setContentType(MediaType.valueOf(contentType));
        headers.add("Content-disposition", "inline; filename=" + fileName + "");

        return headers;
    }

    private Song songFromMultipartFile(MultipartFile song) throws IOException {
        String songOriginalName = song.getOriginalFilename();
        Song songToBeSaved = new Song();

        songToBeSaved.setName(SongUtil.getFileName(Objects.requireNonNull(songOriginalName)));
        songToBeSaved.setExtension(SongUtil.getFileExtension(Objects.requireNonNull(songOriginalName)));
        songToBeSaved.setOriginalName(songOriginalName);
        songToBeSaved.setContentType(song.getContentType());
        songToBeSaved.setSong(songToBeSaved.getSong());
        return songToBeSaved;
    }
}
