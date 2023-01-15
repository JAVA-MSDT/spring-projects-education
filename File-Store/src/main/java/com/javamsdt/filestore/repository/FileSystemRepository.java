package com.javamsdt.filestore.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Repository;

@Repository
public class FileSystemRepository {
  @Value("${info.root-dir}")
  private String root;
  @Value("${upload.path}")
  private String uploadPath;
  String RESOURCES_DIR = new FileSystemResource("").getFile().getAbsolutePath();

  @PostConstruct
  public void init() {
    try {
      Files.createDirectories(Paths.get(root + uploadPath));
    } catch (IOException e) {
      throw new RuntimeException("Unable to create upload folder!");
    }
  }

  public String save(byte[] content, String imageName) throws Exception {
    System.out.println("Root:: " + root + ", RESOURCES_DIR:: " + RESOURCES_DIR);
    Path uploadsFolder = Paths.get(root + uploadPath);
    if (!Files.exists(uploadsFolder)) {
      init();
    }
    // Files.createDirectories(newFile.getParent());
    Path newFile = Paths.get(root + uploadPath + new Date().getTime() + "-" + imageName);
    Files.write(newFile, content);

    return newFile.toAbsolutePath()
      .toString();
  }

  public FileSystemResource findInFileSystem(String location) {
    try {
      return new FileSystemResource(Paths.get(location));
    } catch (Exception e) {
      // Handle access or file not found problems.
      throw new RuntimeException();
    }
  }
}
