package com.javamsdt.filestore.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class FileStoreUtil {

    public static List<String> extractFileName(String filePath) {
        int lastDot = filePath.lastIndexOf(".");
        return List.of(filePath.substring(0, lastDot), filePath.substring(lastDot));
    }

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
        if(fileName == null) {
            return defaultExtension;
        }
        int lastIndex = getLastIndex(fileName);
        if (lastIndex > 0) {
            return fileName.substring(lastIndex + 1);
        }
        return defaultExtension;
    }

    public static String getBaseNameWithoutExtension(String fileName) {
        String defaultFileName = "file";
        if(fileName == null) {
            return defaultFileName;
        }
        int lastIndex = getLastIndex(fileName);
        if (lastIndex > 0) {
            return fileName.substring(0, lastIndex);
        }
        return defaultFileName;
    }

    private static int getLastIndex(String fileName) {
        return fileName.lastIndexOf(".");
    }
    public static String buildFileUrl(HttpServletRequest request, Long id) {
        return request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath()
                + request.getRequestURI() + "/"
                + id;
    }
}
