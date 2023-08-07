package com.javamsdt.song.downstream.api;

import com.javamsdt.song.model.Song;
import com.javamsdt.song.util.SongUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class SongApi {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${downstream.url.resources-processor}")
    private String mp3MetadataUrl;

    @Value("${downstream.url.resources}")
    private String resourcesApi;

    public Song saveSong(MultipartFile multipartSong) throws IOException {

        String songName = multipartSong.getOriginalFilename();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        File tempFile = File.createTempFile(SongUtil.getFileName(Objects.requireNonNull(songName)), SongUtil.getFileExtension(Objects.requireNonNull(songName)));
        Resource fileSystemResource = SongUtil.fileSystemResource(multipartSong, tempFile);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("resource", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Song> songResponseEntity = restTemplate.exchange(resourcesApi, HttpMethod.POST, requestEntity, Song.class);
        // System.out.println("Temp Deleted:: " + tempFile.delete());

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
