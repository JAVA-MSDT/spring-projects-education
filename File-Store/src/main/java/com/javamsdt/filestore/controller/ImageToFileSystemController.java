package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.service.ImageToFileSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/V1/image-system")
@RequiredArgsConstructor
public class ImageToFileSystemController {

  private final ImageToFileSystemService imageToFileSystemService;

  @PostMapping
  public ResponseEntity<?> uploadFile(@RequestParam("image") MultipartFile file) {
    try {
      imageToFileSystemService.save(file);

      return ResponseEntity.status(HttpStatus.OK)
        .body("Uploaded the file successfully: " + file.getOriginalFilename());
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
        .body("Could not upload the file: " + file.getOriginalFilename() + "!");
    }
  }
}
