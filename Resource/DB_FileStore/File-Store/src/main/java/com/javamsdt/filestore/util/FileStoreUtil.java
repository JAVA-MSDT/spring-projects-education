package com.javamsdt.filestore.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileStoreUtil {

    public static String getContentType(String fileName) {
        String defaultContentType = "application/octet-stream";
        String extension = getFileExtension(fileName, defaultContentType);
        try {
            String contentType = Files.probeContentType(Paths.get("filename." + extension));
            if (contentType != null) {
                return contentType;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return defaultContentType;
    }

    public static String getFileExtension(String fileName, String defaultExtension) {
        if (fileName == null) {
            return defaultExtension;
        }
        int lastIndex = getLastIndex(fileName);
        if (lastIndex > 0) {
            return fileName.substring(lastIndex + 1);
        }
        return defaultExtension;
    }

    public static String getFileNameWithoutExtension(String fileName) {
        String defaultFileName = "file";
        if (fileName == null) {
            return defaultFileName;
        }
        int lastIndex = getLastIndex(fileName);
        if (lastIndex > 0) {
            return fileName.substring(0, lastIndex);
        }
        return defaultFileName;
    }

    public static String saveFileToFolder(MultipartFile file, String path) throws IOException {
        String fileName = file.getOriginalFilename();
        Path imagePath = Paths.get(path + fileName);
        Files.createDirectories(imagePath.getParent());
        file.transferTo(imagePath.toFile());
        return fileName;
    }

    private static int getLastIndex(String fileName) {
        return fileName.lastIndexOf(".");
    }
}
