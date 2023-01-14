package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/images")
public class ImageController {
  @Autowired
  ImageRepository imageRepository;

  @PostMapping
  Long uploadImage(@RequestParam("image") MultipartFile multipartImage) throws Exception {
    Image dbImage = new Image();
    dbImage.setName(multipartImage.getName());
    dbImage.setContent(multipartImage.getBytes());

    return imageRepository.save(dbImage)
      .getId();
  }

  @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  Resource downloadImage(@PathVariable Long imageId) {
    byte[] image = imageRepository.findById(imageId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND))
      .getContent();

    return new ByteArrayResource(image);
  }

  @GetMapping(value = "/image/{imageId}")
  ImageDto downloadImageById(@PathVariable Long imageId) {
Image image = imageRepository.findById(imageId)
  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

    return new ImageDto(image.getId(), new ByteArrayResource(image.getContent()), image.getName(), image.getLocation());
  }
}
