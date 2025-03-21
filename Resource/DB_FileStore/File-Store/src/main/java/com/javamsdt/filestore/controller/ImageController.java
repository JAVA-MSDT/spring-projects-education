package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("${app.api.image}")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    ImageDto saveImage(@RequestParam("alt") String alt, @RequestParam("image") MultipartFile multipartImage) throws Exception {
        return imageService.saveImage(multipartImage, alt);
    }

    @GetMapping(value = "/{id}")
    ResponseEntity<Resource> findImageById(@PathVariable("id") Long id) {
        ImageDto imageDto = imageService.findImageById(id);
        byte[] image = imageDto.getContent();
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(imageDto.getContentType()))
                .body(new ByteArrayResource(image));
    }

    @GetMapping("/download-by-id/{id}")
    public ResponseEntity<Resource> downloadImageById(@PathVariable Long id) {
        return imageService.getImageResourceById(id);
    }

    @GetMapping("/download-by-name/{name}")
    public ResponseEntity<Resource> downloadImageById(@PathVariable("name") String name) {
        return imageService.getImageResourceByName(name);
    }

    @GetMapping
    ResponseEntity<List<ImageDto>> findAllImages() {
        return new ResponseEntity<>(imageService.findAllImages(), HttpStatus.OK);
    }

    @GetMapping("/ids")
    ResponseEntity<List<ImageDto>> findImagesByIds(@RequestParam("ids") List<Long> ids) {
        return new ResponseEntity<>(imageService.findByImageIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/name/{name}")
    ResponseEntity<Resource> findImageByName(@PathVariable("name") String name) {
        ImageDto image = imageService.findImageByName(name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(image.getContentType()))
                .body(new ByteArrayResource(image.getContent()));
    }
}
