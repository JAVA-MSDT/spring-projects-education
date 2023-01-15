package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.service.ImageLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("V1/filesystem")
@RequiredArgsConstructor
public class FileSystemImageController {

  private final ImageLocationService imageLocationService;

  @PostMapping("/image")
  Long uploadImage(@RequestParam("image") MultipartFile image) throws Exception {
    return imageLocationService.save(image.getBytes(), image.getOriginalFilename());
  }

  @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  FileSystemResource downloadImage(@PathVariable Long imageId) throws Exception {
    return imageLocationService.find(imageId);
  }
}
