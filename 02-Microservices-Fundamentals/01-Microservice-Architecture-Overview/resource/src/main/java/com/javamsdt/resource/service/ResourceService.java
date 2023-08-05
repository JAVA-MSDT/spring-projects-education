package com.javamsdt.resource.service;

import com.javamsdt.resource.awsservice.StorageService;
import com.javamsdt.resource.model.Resource;
import com.javamsdt.resource.repository.ResourceRepository;
import com.javamsdt.resource.util.ResourceUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class ResourceService {
    private final ResourceRepository resourceRepository;
    private final StorageService storageService;

    public Resource getResourceById(Long id) {
        return findResourceById(id);
    }

    public List<Resource> getResources() {
        return resourceRepository.findAll();
    }

    public Resource saveResource(MultipartFile resource) throws IOException {
        return resourceRepository.save(resourceFromMultipartFile(resource));
    }

    public ResponseEntity<?> deleteResources(Long[] ids) {
        List<String> messages = new ArrayList<>();
        Arrays.stream(ids)
                .forEach(id -> {
                    try {
                        deleteResource(id);
                    } catch (ResponseStatusException responseStatusException) {
                        messages.add(responseStatusException.getMessage());
                    }
                });
        return messages.isEmpty() ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(messages, HttpStatus.MULTI_STATUS);
    }

    public void deleteResource(Long id) {
        resourceRepository.delete(findResourceById(id));
    }

    private Resource findResourceById(Long id) {
        return resourceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Resource with that id: %s is Not found in DB", id)));
    }

    private Resource resourceFromMultipartFile(MultipartFile resource) throws IOException {
        String resourceOriginalName = resource.getOriginalFilename();
        Resource resourceToBeSaved = new Resource();

        resourceToBeSaved.setName(ResourceUtil.getFileName(Objects.requireNonNull(resourceOriginalName)));
        resourceToBeSaved.setExtension(ResourceUtil.getFileExtension(Objects.requireNonNull(resourceOriginalName)));
        resourceToBeSaved.setOriginalName(resourceOriginalName);
        resourceToBeSaved.setContentType(resource.getContentType());
        resourceToBeSaved.setUrl(storageService.upload(resource));
        return resourceToBeSaved;
    }
}
