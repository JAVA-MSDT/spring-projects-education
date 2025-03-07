package com.clothesshop.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResourcesUtil {

    public static String saveImageToFolder(MultipartFile clotheImage, String path) throws IOException {
        String imageName = clotheImage.getOriginalFilename();
        Path imagePath = Paths.get(path + imageName);
        Files.createDirectories(imagePath.getParent());
        clotheImage.transferTo(imagePath.toFile());
        return imageName;
    }
}
