package com.javamsdt.filestore.controller;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.service.ImageToDbService;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
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
  Long uploadImage(@RequestParam("image") MultipartFile multipartImage) throws Exception {
    return imageToDbService.saveImageReturnId(multipartImage);
  }

  @GetMapping(value = "/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  Resource downloadImage(@PathVariable Long imageId) {
    byte[] image = imageToDbService.getImageById(imageId)
      .getContent();
    return new ByteArrayResource(image);
  }

  @GetMapping(produces = MediaType.IMAGE_JPEG_VALUE)
  List<Resource> downloadImage(@RequestParam("ids") List<Long> ids) {
    return imageToDbService.findByImageIds(ids)
      .stream()
      .peek(image -> System.out.println(image.getName()))
      .map(Image::getContent)
      .map(ByteArrayResource::new)
      .collect(Collectors.toList());
  }

  @GetMapping(value = "/image-name/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
  Resource downloadImageByName(@PathVariable("name") String name) {
    byte[] image = imageToDbService.findImageByName(name)
      .getContent();
    return new ByteArrayResource(image);
  }

  @GetMapping(value = "/image/{imageId}", produces = MediaType.IMAGE_JPEG_VALUE)
  ImageDto downloadImageById(@PathVariable Long imageId) {
    return imageToDbService.downloadImageById(imageId);
  }

  @GetMapping(value = "/image-resource/{imageId}")
  public ResponseEntity<Resource> getImageAsResource(@PathVariable Long imageId) {
    Image image = imageToDbService.getImageById(imageId);

    HttpHeaders headers = new HttpHeaders();
    headers.add("imageName", image.getName());
    Resource resource = new ByteArrayResource(image.getContent());
    // new ServletContextResource(servletContext, "/WEB-INF/images/image-example.jpg");
    return new ResponseEntity<>(resource, headers, HttpStatus.OK);
  }

  @GetMapping(value = "/image-response-entity/{imageId}")
  public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable Long imageId) {
    Image image = imageToDbService.getImageById(imageId);

    HttpHeaders headers = new HttpHeaders();
    headers.add("imageName", image.getName());
    // InputStream in = servletContext.getResourceAsStream("/WEB-INF/images/image-example.jpg");
    // byte[] media = IOUtils.toByteArray(in);
    headers.setCacheControl(CacheControl.noCache().getHeaderValue());

    return new ResponseEntity<>(image.getContent(), headers, HttpStatus.OK);
  }
}
