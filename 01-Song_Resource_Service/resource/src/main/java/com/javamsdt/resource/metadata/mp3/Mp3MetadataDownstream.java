package com.javamsdt.resource.metadata.mp3;

import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Mp3MetadataDownstream {
    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${mp3.metadata.url}")
    private String mp3MetadataUrl;

    public Mp3Metadata saveMp3Metadata(Mp3Metadata metadata) {
        ResponseEntity<Mp3Metadata> mp3MetadataResponseEntity = restTemplate.postForEntity(mp3MetadataUrl, metadata, Mp3Metadata.class);
        return mp3MetadataResponseEntity.getBody();
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
