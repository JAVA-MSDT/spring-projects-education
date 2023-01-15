package com.javamsdt.filestore.service;

import com.javamsdt.filestore.model.Image;
import com.javamsdt.filestore.repository.FileSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ImageLocationService {

  private final FileSystemRepository fileSystemRepository;
  private final ImageToDbService imageToDbService;

  public Long save(byte[] bytes, String imageName) throws Exception {
    String location = fileSystemRepository.save(bytes, imageName);
    System.out.println("saveImageReturnIdFromImage:: " + location);
    Image image = new Image();
    image.setLocation(location);
    image.setName(imageName);
    return imageToDbService.saveImageReturnIdFromImage(image);
  }

  public FileSystemResource find(Long imageId) {
    Image image = imageToDbService.getImageById(imageId);

    return fileSystemRepository.findInFileSystem(image.getLocation());
  }

}
