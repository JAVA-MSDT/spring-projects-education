package com.javamsdt.song.downstream.api;

import com.javamsdt.song.downstream.model.Mp3Metadata;
import com.javamsdt.song.model.Song;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SongApi {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${downstream.url.resources-processor}")
    private String mp3MetadataUrl;

    @Value("${downstream.url.resources}")
    private String resourcesApi;

    public Song saveSong(MultipartFile multipartSong) {
        ResponseEntity<Song> songResponseEntity = restTemplate.postForEntity(resourcesApi, multipartSong, Song.class);
        return songResponseEntity.getBody();
    }

    public Song getSongByResourceId(Long resourceId) {
        ResponseEntity<Song> mp3MetadataResponseEntity = restTemplate.getForEntity(resourcesApi + "/" + resourceId, Song.class);
        return mp3MetadataResponseEntity.getBody();
    }
    public List<Song> getSongsByExtension(String extension) {
        ResponseEntity<?> songResponseEntity = restTemplate.getForEntity(resourcesApi + "/extension/" + extension, List.class);
        return (List<Song>) songResponseEntity.getBody();
    }

    public List<String> deleteMp3Metadata(Long[] resourceId) {
        HttpEntity<?> entity = HttpEntity.EMPTY;
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(mp3MetadataUrl + "/delete-metadata-by-resources-ids")
                .queryParam("resources-ids", (Object[]) resourceId)
                .encode()
                .toUriString();
        ResponseEntity<?> mp3MetadataResponseEntity = restTemplate.exchange(urlTemplate, HttpMethod.DELETE, HttpEntity.EMPTY, List.class);

        int responseStatusCode = mp3MetadataResponseEntity.getStatusCode().value();
        if (responseStatusCode == 200) {
            return new ArrayList<>();
        }
        return (List<String>) mp3MetadataResponseEntity.getBody();
    }


}
