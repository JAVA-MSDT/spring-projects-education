package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.service.ImageService;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
