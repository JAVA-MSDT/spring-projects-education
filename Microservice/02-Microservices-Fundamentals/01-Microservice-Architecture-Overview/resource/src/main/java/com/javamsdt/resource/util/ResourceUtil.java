package com.javamsdt.resource.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ResourceUtil {

    public static String getFileName(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf(AppConstants.DOT));
    }

    public static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(AppConstants.DOT));
    }

    public static boolean checkFileExtension(MultipartFile file, String extension) {
        return getFileExtension
                (Objects.requireNonNull(file.getOriginalFilename()))
                .equalsIgnoreCase(extension);
    }
}
