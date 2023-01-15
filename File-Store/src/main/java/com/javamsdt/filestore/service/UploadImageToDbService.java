package com.javamsdt.filestore.service;

import com.javamsdt.filestore.dto.ImageDto;
import com.javamsdt.filestore.mapper.ImageMapper;
import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.repository.ImageRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UploadImageToDbService {
  private final ImageRepository imageRepository;

  private final ImageMapper imageMapper;

  public Long saveImageReturnId(MultipartFile multipartImage) throws Exception {
    System.out.println(multipartImage.getName());
    System.out.println(extractImageName(Objects.requireNonNull(multipartImage.getOriginalFilename())));
    Image dbImage = new Image();
    dbImage.setName(extractImageName(multipartImage.getOriginalFilename()));
    dbImage.setContent(multipartImage.getBytes());

    return imageRepository.save(dbImage)
      .getId();
  }

  public Image getImageById(Long imageId) {
    return imageRepository.findById(imageId)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public Image findImageByName(String name) {
    return imageRepository.findByName(name)
      .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
  }

  public ImageDto downloadImageById(Long imageId) {
    return imageMapper.toImageDto(getImageById(imageId));
  }

  private String extractImageName(String imagePath) {
    int lastDot = imagePath.lastIndexOf(".");
    return imagePath.substring(0, lastDot);
  }

}
