package com.javamsdt.resource.metadata.mp3;

import com.javamsdt.resource.metadata.mp3.model.Mp3Metadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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


}
