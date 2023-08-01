package com.javamsdt.resourcemedata.controller;

import com.javamsdt.resourcemedata.model.Mp3Metadata;
import com.javamsdt.resourcemedata.service.Mp3MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("${api.version}/mp3-metadata")
@RequiredArgsConstructor
public class Mp3MetadataController {

    private final Mp3MetadataService mp3MetadataService;

    @GetMapping("/resource/{resourceId}")
    public Mp3Metadata getMp3MetadataByResourceId(@PathVariable Long resourceId) {
        return mp3MetadataService.getMp3MetadataByResourceId(resourceId);
    }

    @GetMapping()
    public List<Mp3Metadata> getMp3Metadata() {
        return mp3MetadataService.getAllMp3Metadata();
    }

    @PostMapping
    public Mp3Metadata saveMp3Metadata(@RequestBody Mp3Metadata metadata) {
        return mp3MetadataService.saveMp3Metadata(metadata);
    }

    @DeleteMapping("/delete-metadata-by-resources-ids")
    public ResponseEntity<?> deleteMp3MetadataByResourcesId(@RequestParam(name = "resources-ids") Long[] ids) {
        List<String> messages = new ArrayList<>();
        Arrays.stream(ids)
                .forEach(id -> {
                    try {
                        mp3MetadataService.deleteMp3MetadataByResourcesId(id);
                    } catch (ResponseStatusException responseStatusException) {
                        messages.add(responseStatusException.getMessage());
                    }
                });
        return messages.isEmpty() ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(messages, HttpStatus.MULTI_STATUS);
    }
}
