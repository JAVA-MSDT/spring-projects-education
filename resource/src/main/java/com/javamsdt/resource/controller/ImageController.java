package com.javamsdt.resource.controller;

import com.javamsdt.resource.model.image.Image;
import com.javamsdt.resource.service.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("${api.version}/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<Image> saveImage(@RequestPart(name = "image") MultipartFile image, @RequestPart(name = "metadata") String metadata) throws IOException {

        return ResponseEntity.ok(imageService.saveImage(image));
    }

    @GetMapping("/content/{id}")
    public ResponseEntity<byte[]> getImageContentById(@PathVariable("id") Long id) throws IOException {
        Image image = imageService.getImageById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(image.getImage());
    }

    @GetMapping("/content/name/{name}")
    public ResponseEntity<byte[]> getImageContentById(@PathVariable("name") String name) throws IOException {
        Image image = imageService.findImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf(image.getContentType()))
                .body(image.getImage());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable("id") Long id) throws IOException {
        Image image = imageService.getImageById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Image> getImageByName(@PathVariable("name") String name) throws IOException {
        Image image = imageService.findImageByName(name);

        return ResponseEntity.status(HttpStatus.OK)
                .body(image);
    }
}
