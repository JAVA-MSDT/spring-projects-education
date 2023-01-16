package com.javamsdt.filestore.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageToDbService imageToDbService;

    private final ServletContext servletContext;

    @PostMapping
    String saveImage(@RequestParam("image") MultipartFile multipartImage, HttpServletRequest request) throws Exception {
        return buildImageUrl(request, imageToDbService.saveImageReturnId(multipartImage));
    }

    @PostMapping("/images")
    List<Long> saveImages(@RequestParam("images") MultipartFile[] multipartImage) throws Exception {
        List<MultipartFile> multipartFiles = Arrays.stream(multipartImage).collect(Collectors.toList());
        System.out.println("multipartImage.size():: " + multipartFiles.size());
        return imageToDbService.saveImages(multipartFiles);
    }

    @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource findImageById(@PathVariable Long imageId) {
        byte[] image = imageToDbService.getImageById(imageId)
                .getContent();
        return new ByteArrayResource(image);
    }

    @GetMapping
    ResponseEntity<List<String>> findImagesByIdsResource(@RequestParam("ids") List<Long> ids,
            HttpServletRequest request) {
        List<String> resources = imageToDbService.findByImageIds(ids)
                .stream()
                .peek(image -> System.out.println(image.getName()))
                .map(image -> buildImageUrl(request, image.getId()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }

    @GetMapping(value = "/image-name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    Resource findImageByName(@PathVariable("name") String name) {
        byte[] image = imageToDbService.findImageByName(name)
                .getContent();
        return new ByteArrayResource(image);
    }

    private String buildImageUrl(HttpServletRequest request, Long id) {
        return request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath()
                + request.getRequestURI() + "/"
                + id;
    }
}
