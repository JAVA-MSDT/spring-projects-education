package com.javamsdt.resource.service;

import com.javamsdt.resource.metadata.mp3.Mp3MetadataDownstream;
import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import com.javamsdt.resource.metadata.mp3.service.Mp3MetadataService;
import com.javamsdt.resource.model.Song;
import com.javamsdt.resource.repository.SongRepository;
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

import static com.javamsdt.resource.util.ResourceUtil.getMp3Extension;

@Service
@RequiredArgsConstructor
@Slf4j
public class SongService {
    private static final String REST_CLIENT_EXCEPTION_MESSAGE = "restClientException While";
    private final SongRepository songRepository;
    private final Mp3MetadataService mp3MetadataService;
    private final Mp3MetadataDownstream mp3MetadataDownstream;

    public Song getSong(Long id) {
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
        Song song = new Song();
        song.setSong(multipartSong.getBytes());
        Song saved = songRepository.save(song);
        Mp3Metadata mp3Metadata = mp3MetadataService.getMp3Metadata(multipartSong.getInputStream(), saved.getId());
        System.out.println(mp3Metadata);
        try {
            mp3MetadataDownstream.saveMp3Metadata(mp3Metadata);
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
            List<String> deletedMetadata = mp3MetadataDownstream.deleteMp3Metadata(ids);
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
            Mp3Metadata mp3Metadata = mp3MetadataDownstream.getMp3MetadataByResourceId(resourceId);
            fileName = URLEncoder.encode(mp3Metadata.getTitle(), StandardCharsets.UTF_8) + "." + getMp3Extension(mp3Metadata.getAudioCompressor());
            contentType = mp3Metadata.getContentType();

        } catch (RestClientException restClientException) {
            log.error(REST_CLIENT_EXCEPTION_MESSAGE + " getMp3MetadataByResourceId:: " + restClientException.getMessage(), restClientException);
            try {
                Metadata metadata = mp3MetadataService.getResourceMetadataFromInputStream(song.getInputStream());
                fileName = URLEncoder.encode(metadata.get("dc:title"), StandardCharsets.UTF_8) + "." + getMp3Extension(metadata.get("xmpDM:audioCompressor"));
                contentType = metadata.get("Content-Type");
            } catch (IOException e) {
                log.error("IOException while reading inputStream for getting Mp3Metadata:: " + e.getMessage(), e);
            }

        }
        headers.setContentType(MediaType.valueOf(contentType));
        headers.add("Content-disposition", "inline; filename=" + fileName + "");

        return headers;
    }


}
