package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.service.ImageToDbService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

  private final ImageToDbService imageToDbService;

  @PostMapping
  Long saveImage(@RequestParam("image") MultipartFile multipartImage) throws Exception {
    return imageToDbService.saveImageReturnId(multipartImage);
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
  ResponseEntity<List<Resource>> findImagesByIdsResource(@RequestParam("ids") List<Long> ids) {
    List<Resource> resources = imageToDbService.findByImageIds(ids)
      .stream()
      .peek(image -> System.out.println(image.getName()))
      .map(Image::getContent)
      .map(ByteArrayResource::new)
      .collect(Collectors.toList());
    return new ResponseEntity<>(resources, HttpStatus.OK);
  }

  @GetMapping("/allImages")
  ResponseEntity<List<Image>> findImagesByIds(@RequestParam("ids") List<Long> ids) {
/*    List<Resource> resources = imageToDbService.findByImageIds(ids)
      .stream()
      .peek(image -> System.out.println(image.getName()))
      .map(Image::getContent)
      .map(ByteArrayResource::new)
      .collect(Collectors.toList())*/;
    return new ResponseEntity<>(imageToDbService.findByImageIds(ids), HttpStatus.OK);
  }

  @GetMapping(value = "/image-name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
  Resource findImageByName(@PathVariable("name") String name) {
    byte[] image = imageToDbService.findImageByName(name)
      .getContent();
    return new ByteArrayResource(image);
  }
}
