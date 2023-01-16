package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.service.ImageService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping
    ImageDto saveImage(@RequestParam("alt") String alt, @RequestParam("image") MultipartFile multipartImage,
            HttpServletRequest request) throws Exception {
        return imageService.saveImage(multipartImage, alt, request);
    }

    @GetMapping(value = "/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    Resource findImageById(@PathVariable("id") Long id) {
        byte[] image = imageService.findImageById(id)
                .getContent();
        return new ByteArrayResource(image);
    }
    @GetMapping
    ResponseEntity<List<ImageDto>> findAllImages() {
        return new ResponseEntity<>(imageService.findAllImages(), HttpStatus.OK);
    }

    @GetMapping("/ids")
    ResponseEntity<List<ImageDto>> findImagesByIds(@RequestParam("ids") List<Long> ids) {
        return new ResponseEntity<>(imageService.findByImageIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/image-name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource findImageByName(@PathVariable("name") String name) {
        byte[] image = imageService.findImageByName(name)
                .getContent();
        return new ByteArrayResource(image);
    }
}
