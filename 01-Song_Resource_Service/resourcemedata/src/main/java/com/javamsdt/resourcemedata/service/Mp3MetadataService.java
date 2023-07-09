package com.javamsdt.resourcemedata.service;

import com.javamsdt.resourcemedata.model.Mp3Metadata;
import com.javamsdt.resourcemedata.repository.Mp3MetadataRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class Mp3MetadataService {

    private final Mp3MetadataRepository mp3MetadataRepository;

    public Mp3Metadata getMp3MetadataByResourceId(Long resourceId) {
        return findByResourceId(resourceId);
    }
    public List<Mp3Metadata> getAllMp3Metadata() {
        return mp3MetadataRepository.findAll();
    }

    public Mp3Metadata saveMp3Metadata(Mp3Metadata metadata) {
        return mp3MetadataRepository.save(metadata);
    }

    public void deleteMp3MetadataByResourcesId(Long resourceId) {
        mp3MetadataRepository.delete(findByResourceId(resourceId));
    }

    private Mp3Metadata findByResourceId(Long resourceId) {
        return mp3MetadataRepository
                .findByResourceId(resourceId)
                .orElseThrow(() ->
                        getNotFoundResponseStatusException(String.format("Metadata with that resource id: %s is Not found in DB", resourceId)));
    }

    private ResponseStatusException getNotFoundResponseStatusException(String message) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, message);
    }


}
