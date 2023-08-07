package com.javamsdt.song.downstream.api;

import com.javamsdt.song.downstream.model.Mp3Metadata;
import com.javamsdt.song.model.Song;
import com.javamsdt.song.util.SongUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
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
public class Mp3MetadataApi {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${downstream.url.resources-processor}")
    private String mp3MetadataUrl;

    public Mp3Metadata saveMp3Metadata(MultipartFile multipartSong, Long resourceId) throws IOException {

        String songName = multipartSong.getOriginalFilename();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        File tempFile = File.createTempFile(SongUtil.getFileName(Objects.requireNonNull(songName)), SongUtil.getFileExtension(Objects.requireNonNull(songName)));
        Resource fileSystemResource = SongUtil.fileSystemResource(multipartSong, tempFile);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("mp3File", fileSystemResource);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<Mp3Metadata> songResponseEntity = restTemplate.exchange(mp3MetadataUrl + "/" + resourceId, HttpMethod.POST, requestEntity, Mp3Metadata.class);
        System.out.println("Temp Deleted:: " + tempFile.delete());
        return songResponseEntity.getBody();
    }

    public Mp3Metadata getMp3MetadataByResourceId(Long resourceId) {
        ResponseEntity<Mp3Metadata> mp3MetadataResponseEntity = restTemplate.getForEntity(mp3MetadataUrl + "/resource/" + resourceId, Mp3Metadata.class);
        return mp3MetadataResponseEntity.getBody();
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
