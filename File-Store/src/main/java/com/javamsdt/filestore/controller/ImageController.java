package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.service.ImageToDbService;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageToDbService imageToDbService;

    @PostMapping
    ImageDto saveImage(@RequestParam("alt") String alt, @RequestParam("image") MultipartFile multipartImage,
            HttpServletRequest request) throws Exception {
        return imageToDbService.saveImage(multipartImage, alt, request);
    }

    @GetMapping("/{id}")
    ResponseEntity<ImageDto> findImageById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(imageToDbService.findImageById(id), HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<ImageDto>> findAllImages() {
        return new ResponseEntity<>(imageToDbService.findAllImages(), HttpStatus.OK);
    }

    @GetMapping("/ids")
    ResponseEntity<List<ImageDto>> findImagesByIds(@RequestParam("ids") List<Long> ids) {
        return new ResponseEntity<>(imageToDbService.findByImageIds(ids), HttpStatus.OK);
    }

    @GetMapping(value = "/image-name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource findImageByName(@PathVariable("name") String name) {
        byte[] image = imageToDbService.findImageByName(name)
                .getContent();
        return new ByteArrayResource(image);
    }
}
